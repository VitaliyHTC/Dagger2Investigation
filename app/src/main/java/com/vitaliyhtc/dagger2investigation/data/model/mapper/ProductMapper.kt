package com.vitaliyhtc.dagger2investigation.data.model.mapper

import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.functions.Function
import com.vitaliyhtc.dagger2investigation.data.model.response.Product as ProductRaw

class ProductMapper : Function<ProductRaw, Product> {

    override fun apply(p: ProductRaw): Product {
        return p.run {
            Product(
                    id,
                    name,
                    tags,
                    price_in_cents,
                    regular_price_in_cents,
                    description,
                    image_thumb_url,
                    image_url,
                    false
            )
        }
    }
}