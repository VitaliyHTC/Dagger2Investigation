package com.vitaliyhtc.dagger2investigation.data.model.response

data class Product(var id: Int,
                   var name: String,
                   var tags: String,
                   var price_in_cents: Int,
                   var regular_price_in_cents: Int,
                   var description: String?,
                   var image_thumb_url: String?,
                   var image_url: String?)

data class ProductResult(var status: Int,
                         var message: String,
                         var result: Product)

data class ProductsResult(var status: Int,
                          var message: String?,
                          //var pager: Pager,
                          var result: List<Product>,
                          var suggestion: String?)
