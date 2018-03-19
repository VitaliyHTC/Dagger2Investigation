package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function

class ProductMapper : Function<com.vitaliyhtc.dagger2investigation.data.model.response.Product, Product> {

    override fun apply(p: com.vitaliyhtc.dagger2investigation.data.model.response.Product): Product {
        return Product(
                p.id,
                p.name,
                p.tags,
                p.price_in_cents,
                p.regular_price_in_cents,
                p.description,
                p.image_thumb_url,
                p.image_url
        )
    }
}