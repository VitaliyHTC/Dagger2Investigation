package com.vitaliyhtc.dagger2investigation.data.model.response

data class Pager(var records_per_page: Int,
                 var total_record_count: Int,
                 var current_page_record_count: Int,
                 var is_first_page: Boolean,
                 var is_final_page: Boolean,
                 var current_page: Int,
                 var current_page_path: String,
                 var next_page: Int,
                 var next_page_path: String,
                 var previous_page: Int,
                 var previous_page_path: String,
                 var total_pages: Int,
                 var total_pages_path: String)

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
                          var pager: Pager,
                          var result: List<Product>,
                          var suggestion: String?)
