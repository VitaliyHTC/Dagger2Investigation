package com.vitaliyhtc.dagger2investigation.domain.model

data class Product(var id: Int,
                   var name: String,
                   var tags: String,
                   var price_in_cents: Int,
                   var regular_price_in_cents: Int,
                   var description: String?,
                   var image_thumb_url: String?,
                   var image_url: String?)