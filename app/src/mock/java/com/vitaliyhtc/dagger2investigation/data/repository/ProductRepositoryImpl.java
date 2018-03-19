package com.vitaliyhtc.dagger2investigation.data.repository;

import com.google.gson.Gson;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.global.Dagger2InvestigationApp;
import com.vitaliyhtc.dagger2investigation.utils.AssetsUtils;

import java.util.List;

import io.reactivex.Single;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String MOCK_PRODUCTS_ASSET_NAME = "ProductsPage.json";

    private ApiInterface mApiService;
    //private final ProductMapper mProductMapper;
    private final ProductsMapper mProductsMapper;

    private List<Product> mProducts;


    public ProductRepositoryImpl(ApiInterface apiService) {
        mApiService = apiService;
        //mProductMapper = new ProductMapper();
        mProductsMapper = new ProductsMapper();
        initData();
    }

    private void initData() {
        String json = AssetsUtils.readStringFromAsset(
                Dagger2InvestigationApp.getInstance().getApplicationContext(),
                MOCK_PRODUCTS_ASSET_NAME
        );
        Gson gson = new Gson();
        ProductsResult productsResult = gson.fromJson(json, ProductsResult.class);

        try {
            mProducts = mProductsMapper.apply(productsResult.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Single<List<Product>> getProducts(int page) {
        return Single.just(mProducts);
    }

    @Override
    public Single<Product> getOneProduct(int productId) {
        Product product = null;
        for (Product p : mProducts) {
            if (p.getId() == productId) product = p;
        }
        return Single.just(product);
    }
}