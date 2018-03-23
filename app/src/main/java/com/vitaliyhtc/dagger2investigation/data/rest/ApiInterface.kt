package com.vitaliyhtc.dagger2investigation.data.rest

import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult
import io.reactivex.Single
import retrofit2.Call
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

    // TODO: 3/23/18 clean up
    @GET("products")
    fun getProductsSearchWithWResult(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("where_not") whereNot: String,
            @Query("where") where: String,
            @Query("access_key") accessKey: String
    ): Call<ProductsResult>

    @GET("products")
    fun getProductsSearchWithQResult(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("where_not") whereNot: String,
            @Query("q") query: String,
            @Query("access_key") accessKey: String
    ): Call<ProductsResult>

    @GET("products")
    fun getProductsSearchWithQWResult(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("where_not") whereNot: String,
            @Query("q") query: String,
            @Query("where") where: String,
            @Query("access_key") accessKey: String
    ): Call<ProductsResult>

    @GET("products/{product_id}")
    fun getOneProduct(
            @Path("product_id") productId: Int,
            @Query("access_key") accessKey: String
    ): Single<ProductResult>

}