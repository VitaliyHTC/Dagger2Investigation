package com.vitaliyhtc.dagger2investigation.data.repository

import com.vitaliyhtc.dagger2investigation.BuildConfig.LCBO_API_ACCESS_KEY
import com.vitaliyhtc.dagger2investigation.data.db.AppDatabase
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single
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

    override fun getOneProduct(productId: Int): Single<Product> {
        return productDao.getProductById(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }
}