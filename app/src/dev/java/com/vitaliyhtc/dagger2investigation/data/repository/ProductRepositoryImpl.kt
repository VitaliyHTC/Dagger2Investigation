package com.vitaliyhtc.dagger2investigation.data.repository

import com.vitaliyhtc.dagger2investigation.Config.PRODUCTS_PER_PAGE
import com.vitaliyhtc.dagger2investigation.Config.PRODUCTS_WHERE_NOT
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductMapper
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface.LCBO_API_ACCESS_KEY
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single

class ProductRepositoryImpl(private val apiInterface: ApiInterface) : ProductRepository {

    private val mProductMapper: ProductMapper = ProductMapper()
    private val mProductsMapper: ProductsMapper = ProductsMapper()


    override fun getProducts(page: Int): Single<List<Product>> {
        return apiInterface
                .getProductsResult(
                        page,
                        PRODUCTS_PER_PAGE,
                        PRODUCTS_WHERE_NOT,
                        LCBO_API_ACCESS_KEY
                )
                .map(ProductsResult::result)
                .map(mProductsMapper)
    }

    override fun getOneProduct(productId: Int): Single<Product> {
        return apiInterface
                .getOneProduct(
                        productId,
                        LCBO_API_ACCESS_KEY
                )
                .map(ProductResult::result)
                .map(mProductMapper)
    }
}