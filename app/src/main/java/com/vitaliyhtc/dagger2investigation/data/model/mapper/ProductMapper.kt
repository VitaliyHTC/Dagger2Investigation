package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.data.model.response.Product as Product1
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function

class ProductMapper : Function<Product1, Product> {

    override fun apply(p: Product1): Product {
        return p.run {
            Product(
                    id,
                    name,
                    tags,
                    price_in_cents,
                    regular_price_in_cents,
                    description,
                    image_thumb_url,
                    image_url
            )
        }
    }
}