package com.vitaliyhtc.dagger2investigation.data.rest;

import com.vitaliyhtc.dagger2investigation.BuildConfig;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@SuppressWarnings("unused")
public interface ApiInterface {
    String LCBO_API_BASE_URL = BuildConfig.LCBO_API_BASE_URL;
    String LCBO_API_ACCESS_KEY = BuildConfig.LCBO_API_ACCESS_KEY;

    @GET("products")
    Single<ProductsResult> getProductsResult(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("where_not") String whereNot,
            @Query("access_key") String accessKey
    );

    @GET("products")
    Call<ProductsResult> getProductsSearchWithWResult(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("where_not") String whereNot,
            @Query("where") String where,
            @Query("access_key") String accessKey
    );

    @GET("products")
    Call<ProductsResult> getProductsSearchWithQResult(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("where_not") String whereNot,
            @Query("q") String query,
            @Query("access_key") String accessKey
    );

    @GET("products")
    Call<ProductsResult> getProductsSearchWithQWResult(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("where_not") String whereNot,
            @Query("q") String query,
            @Query("where") String where,
            @Query("access_key") String accessKey
    );

    @GET("products/{product_id}")
    Single<ProductResult> getOneProduct(
            @Path("product_id") int productId,
            @Query("access_key") String accessKey
    );

}
