package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function
// TODO: 3/22/18 try alias
class ProductsMapper : Function<List<com.vitaliyhtc.dagger2investigation.data.model.response.Product>, ArrayList<Product>> {

    private val mPm: ProductMapper = ProductMapper()

    override fun apply(products: List<com.vitaliyhtc.dagger2investigation.data.model.response.Product>): ArrayList<Product> {
        // TODO: 3/22/18 chain operation map can be used
        val list: ArrayList<Product> = ArrayList()
        for (product in products) {
            list.add(mPm.apply(product))
        }
        return list
    }
}