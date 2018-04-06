package com.vitaliyhtc.dagger2investigation.data.repository

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
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

    private val mProductDao = appDatabase.productDao()

    private lateinit var mProducts: List<Product>

    init {
        val json = App.getInstance().applicationContext.readStringFromAsset(mockProductsAssetName)
        val gson = Gson()
        val productsResult = gson.fromJson<ProductsResult>(json, ProductsResult::class.java)

        try {
            mProducts = mProductsMapper.apply(productsResult.result)

            Completable.fromAction { mProductDao.insertAll(mProducts) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun subscribeForProductsUpdates(lifecycleOwner: LifecycleOwner, listener: (products: List<Product>) -> Unit) {
        mProductDao.getAllLiveData().observe(lifecycleOwner, Observer<List<Product>> {
            if (it != null) {
                listener.invoke(it)
            }
        })
    }

    override fun subscribeForProductsUpdates(listener: (products: List<Product>) -> Unit) {
        mProductDao.getAllFlowable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { products -> listener.invoke(products) }
    }

    override fun updateProduct(product: Product) {
        Completable.fromAction { mProductDao.update(product) }
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