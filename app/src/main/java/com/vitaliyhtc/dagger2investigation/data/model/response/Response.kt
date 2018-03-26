package com.vitaliyhtc.dagger2investigation.data.model.response

data class Product(val id: Int,
                   val name: String,
                   val tags: String,
                   val price_in_cents: Int,
                   val regular_price_in_cents: Int,
                   val description: String?,
                   val image_thumb_url: String?,
                   val image_url: String?)

data class ProductResult(val status: Int,
                         val message: String,
                         val result: Product)

data class ProductsResult(val status: Int,
                          val message: String?,
                          //val pager: Pager,
                          val result: List<Product>,
                          val suggestion: String?)
