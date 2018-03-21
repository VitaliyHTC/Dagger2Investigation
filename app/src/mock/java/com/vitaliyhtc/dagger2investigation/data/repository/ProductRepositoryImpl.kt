package com.vitaliyhtc.dagger2investigation.data.repository

import com.google.gson.Gson
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.global.Dagger2InvestigationApp
import com.vitaliyhtc.dagger2investigation.utils.readStringFromAsset
import io.reactivex.Single

class ProductRepositoryImpl(private val apiInterface: ApiInterface) : ProductRepository {

    private val mockProductsAssetName = "ProductsPage.json"

    private val mProductsMapper: ProductsMapper = ProductsMapper()

    private lateinit var mProducts: List<Product>

    init {
        val json = readStringFromAsset(
                Dagger2InvestigationApp.getInstance().applicationContext,
                mockProductsAssetName
        )
        val gson = Gson()
        val productsResult = gson.fromJson<ProductsResult>(json, ProductsResult::class.java)

        try {
            mProducts = mProductsMapper.apply(productsResult.result)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getProducts(page: Int): Single<List<Product>> {
        return Single.just(mProducts)
    }

    override fun getOneProduct(productId: Int): Single<Product> {
        return Single.just(mProducts.first { item -> item.id == productId })
    }
}