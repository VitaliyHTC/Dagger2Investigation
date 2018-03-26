package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function
import com.vitaliyhtc.dagger2investigation.data.model.response.Product as ProductApi

class ProductsMapper : Function<List<ProductApi>, List<Product>> {

    private val mapper = ProductMapper()

    override fun apply(products: List<ProductApi>): List<Product> {
        return products.map { mapper.apply(it) }
    }
}