package com.vitaliyhtc.dagger2investigation.domain

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(page: Int): Single<List<Product>>

    fun getOneProduct(productId: Int): Single<Product>

    fun updateProduct(product: Product)
}