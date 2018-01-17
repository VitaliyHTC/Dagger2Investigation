package com.vitaliyhtc.dagger2investigation.data;

import com.vitaliyhtc.dagger2investigation.Config;
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductMapper;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductResult;
import com.vitaliyhtc.dagger2investigation.data.model.response.ProductsResult;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;

import static com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface.LCBO_API_ACCESS_KEY;

public class ProductRepositoryImpl implements ProductRepository {

    private ApiInterface mApiService;
    private final ProductMapper mProductMapper;

    public ProductRepositoryImpl(ApiInterface apiService, ProductMapper productMapper) {
        mApiService = apiService;
        mProductMapper = productMapper;
    }

    @Override
    public Observable<List<Product>> getProductsObservable(int page) {
        return mApiService
                .getProductsResult(
                        page,
                        Config.PRODUCTS_PER_PAGE,
                        Config.PRODUCTS_WHERE_NOT,
                        LCBO_API_ACCESS_KEY
                )
                .toObservable()
                .map(ProductsResult::getResult)
                .flatMap(Observable::fromIterable)
                .map(mProductMapper)
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Product> getOneProductsObservable(int productId) {
        return mApiService
                .getOneProduct(
                        productId,
                        LCBO_API_ACCESS_KEY
                )
                .toObservable()
                .map(ProductResult::getResult)
                .map(mProductMapper);
    }
}