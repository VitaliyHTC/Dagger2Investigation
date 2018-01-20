package com.vitaliyhtc.dagger2investigation.data.repository;

import com.vitaliyhtc.dagger2investigation.Config;
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductMapper;
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductsMapper;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

import io.reactivex.Single;

import static com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface.LCBO_API_ACCESS_KEY;

public class ProductRepositoryImpl implements ProductRepository {

    private ApiInterface mApiService;
    private final ProductMapper mProductMapper;
    private final ProductsMapper mProductsMapper;


    private static List<Product> products = new ArrayList<>;


    public ProductRepositoryImpl(ApiInterface apiService) {
        mApiService = apiService;
        mProductMapper = new ProductMapper();
        mProductsMapper = new ProductsMapper();
    }

    @Override
    public Single<List<Product>> getProducts(int page) {
        return mApiService
                .getProductsResult(
                        page,
                        Config.PRODUCTS_PER_PAGE,
                        Config.PRODUCTS_WHERE_NOT,
                        LCBO_API_ACCESS_KEY
                )
                .map(ProductsResult::getResult)
                .map(mProductsMapper);
    }

    @Override
    public Single<Product> getOneProduct(int productId) {
        return mApiService
                .getOneProduct(
                        productId,
                        LCBO_API_ACCESS_KEY
                )
                .map(ProductResult::getResult)
                .map(mProductMapper);
    }
}