package com.vitaliyhtc.dagger2investigation

object Config {

    // TODO: 3/23/18 use starter pattern (like newInstance for fragment) and you can make this val private
    const val KEY_PRODUCT_ID = "KEY_PRODUCT_ID"

    // TODO: 3/23/18 this can be just private variables, don't need to make them static
    const val PRODUCTS_PER_PAGE: Int = 40
    const val PRODUCTS_WHERE_NOT: String = "is_dead"

    const val NO_VALUE_INT: Int = -1

}