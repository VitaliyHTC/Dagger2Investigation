package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.data.model.response.Product as Product1
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function

class ProductsMapper : Function<List<Product1>, ArrayList<Product>> {

    private val mPm: ProductMapper = ProductMapper()

    override fun apply(products: List<Product1>): ArrayList<Product> {
        return ArrayList(products.map { product -> mPm.apply(product) })
    }
}