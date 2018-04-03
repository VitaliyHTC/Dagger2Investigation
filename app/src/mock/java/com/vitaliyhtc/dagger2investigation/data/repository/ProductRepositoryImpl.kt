package com.vitaliyhtc.dagger2investigation.data.repository

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.test.espresso.idling.CountingIdlingResource
import com.google.gson.Gson
import com.vitaliyhtc.dagger2investigation.data.db.AppDatabase
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.global.App
import com.vitaliyhtc.dagger2investigation.utils.readStringFromAsset
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRepositoryImpl(private val apiInterface: ApiInterface,
                            appDatabase: AppDatabase) : ProductRepository {

    private val mockProductsAssetName = "ProductsPage.json"

    private val mProductsMapper: ProductsMapper = ProductsMapper()

    private val productDao = appDatabase.productDao()

    private lateinit var mProducts: List<Product>

    lateinit var mIdlingResource: CountingIdlingResource

    init {
        val json = App.getInstance().applicationContext.readStringFromAsset(mockProductsAssetName)
        val gson = Gson()
        val productsResult = gson.fromJson<ProductsResult>(json, ProductsResult::class.java)

        try {
            mProducts = mProductsMapper.apply(productsResult.result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun setIdlingResource(idlingResource: CountingIdlingResource) {
        mIdlingResource = idlingResource

        mIdlingResource.increment()
        Completable.fromAction { productDao.insertAll(mProducts) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    override fun subscribeForProductsUpdates(lifecycleOwner: LifecycleOwner, listener: (products: List<Product>) -> Unit) {
        productDao.getAllLiveData().observe(lifecycleOwner, Observer<List<Product>> {
            if (it != null) {
                listener.invoke(it)
                mIdlingResource.decrement()
            }
        })
    }

    override fun subscribeForProductsUpdates(listener: (products: List<Product>) -> Unit) {
        productDao.getAllFlowable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { products ->
                    listener.invoke(products)
                    mIdlingResource.decrement()
                }
    }

    override fun updateProduct(product: Product) {
        Completable.fromAction { productDao.update(product) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    override fun getProducts(page: Int): Single<List<Product>> {
        return Single.just(mProducts)
    }

    override fun getOneProduct(productId: Int): Single<Product> {
        return Single.just(mProducts.first { item -> item.id == productId })
    }
}