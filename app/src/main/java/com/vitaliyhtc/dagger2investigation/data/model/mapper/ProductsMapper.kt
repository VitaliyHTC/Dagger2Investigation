package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function

class ProductsMapper : Function<List<com.vitaliyhtc.dagger2investigation.data.model.response.Product>, ArrayList<Product>> {

    private val mPm: ProductMapper = ProductMapper()

    override fun apply(products: List<com.vitaliyhtc.dagger2investigation.data.model.response.Product>): ArrayList<Product> {
        val list: ArrayList<Product> = ArrayList()
        for (product in products) {
            list.add(mPm.apply(product))
        }
        return list
    }
}