package com.vitaliyhtc.dagger2investigation.domain.model

data class Product(val id: Int,
                   val name: String,
                   val tags: String,
                   val price_in_cents: Int,
                   val regular_price_in_cents: Int,
                   val description: String?,
                   val image_thumb_url: String?,
                   val image_url: String?)