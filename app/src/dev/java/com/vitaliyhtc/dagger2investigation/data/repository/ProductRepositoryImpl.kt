package com.vitaliyhtc.dagger2investigation.data.repository

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.vitaliyhtc.dagger2investigation.BuildConfig.LCBO_API_ACCESS_KEY
import com.vitaliyhtc.dagger2investigation.data.db.AppDatabase
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRepositoryImpl(private val apiInterface: ApiInterface,
                            appDatabase: AppDatabase) : ProductRepository {

    private val productsPerPage: Int = 40
    private val productsWhereNot: String = "is_dead"

    //private val mProductMapper: ProductMapper = ProductMapper()
    private val mProductsMapper: ProductsMapper = ProductsMapper()

    private val productDao = appDatabase.productDao()


    override fun getProducts(page: Int): Single<List<Product>> {
        return apiInterface
                .getProductsResult(
                        page,
                        productsPerPage,
                        productsWhereNot,
                        LCBO_API_ACCESS_KEY
                )
                .map(ProductsResult::result)
                .map(mProductsMapper)
                .doOnSuccess { products: List<Product> -> productDao.insertAll(products) }
    }

    override fun subscribeForProductsUpdates(lifecycleOwner: LifecycleOwner, listener: (products: List<Product>) -> Unit) {
        productDao.getAllLiveData().observe(lifecycleOwner, Observer<List<Product>> { if (it != null) listener.invoke(it) })
    }

    override fun subscribeForProductsUpdates(listener: (products: List<Product>) -> Unit) {
        productDao.getAllFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { products -> listener.invoke(products) }
    }

    override fun getOneProduct(productId: Int): Single<Product> {
        return productDao.getProductById(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun updateProduct(product: Product) {
        Completable.fromAction {
            productDao.update(product)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }
}