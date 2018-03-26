package com.vitaliyhtc.dagger2investigation.data.rest

import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("products")
    fun getProductsResult(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("where_not") whereNot: String,
            @Query("access_key") accessKey: String
    ): Single<ProductsResult>

    @GET("products/{product_id}")
    fun getOneProduct(
            @Path("product_id") productId: Int,
            @Query("access_key") accessKey: String
    ): Single<ProductResult>

}