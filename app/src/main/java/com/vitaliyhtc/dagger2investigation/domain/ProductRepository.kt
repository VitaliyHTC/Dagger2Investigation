package com.vitaliyhtc.dagger2investigation.domain

import android.arch.lifecycle.LifecycleOwner
import android.support.test.espresso.idling.CountingIdlingResource
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single

interface ProductRepository {

    fun setIdlingResource(idlingResource: CountingIdlingResource)

    fun getProducts(page: Int): Single<List<Product>>

    fun subscribeForProductsUpdates(lifecycleOwner: LifecycleOwner, listener: (products: List<Product>) -> Unit)

    fun subscribeForProductsUpdates(listener: (products: List<Product>) -> Unit)

    fun getOneProduct(productId: Int): Single<Product>

    fun updateProduct(product: Product)
}