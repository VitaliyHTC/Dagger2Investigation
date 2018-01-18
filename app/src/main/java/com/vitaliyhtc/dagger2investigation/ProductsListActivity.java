package com.vitaliyhtc.dagger2investigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vitaliyhtc.dagger2investigation.app.Dagger2InvestigationApp;
import com.vitaliyhtc.dagger2investigation.di.component.DaggerMainActivityComponent;
import com.vitaliyhtc.dagger2investigation.di.component.MainActivityComponent;
import com.vitaliyhtc.dagger2investigation.di.module.MainActivityModule;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presenter.ProductsListPresenter;
import com.vitaliyhtc.dagger2investigation.view.ProductsListView;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vitaliyhtc.dagger2investigation.Config.KEY_PRODUCT_ID;

public class ProductsListActivity extends AppCompatActivity implements ProductsListView {

    private ProductsListPresenter mProductsListPresenter;

    ProductRepository mProductRepository;

    ProductsListAdapter mProductsListAdapter;



    @BindView(R.id.rv_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        initDi();

        setAdapter();
        // TODO: 1/17/18 we need to talk about this
        // TODO: inject presenter see seetree & cofeecoin
        // remove presenter
        mProductsListPresenter = new ProductsListPresenter(mProductRepository);
        mProductsListPresenter.onAttachView(this);

        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProductsListPresenter.onDetachView();
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter() {
        mProductsListAdapter = new ProductsListAdapter();
        mProductsListAdapter.setOnProductClickListener(this::onProductClick);
        mRecyclerView.setAdapter(mProductsListAdapter);
    }

    private void initDi() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .productDataComponent(Dagger2InvestigationApp.getInstance().getProductDataComponent())
                .build();
        mProductRepository = mainActivityComponent.getProductRepository();
    }

    private void onProductClick(int productId) {
        mProductsListPresenter.onProductClick(productId);
    }

    private void loadData() {
        mProductsListPresenter.loadData();
    }

    @Override
    public void addProductsToResult(List<Product> products) {
        mProductsListAdapter.appendToProducts(products);
    }



    @Override
    public void loadProductsError(Throwable t) {
        Toast.makeText(this, "ERROR: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchProductDetailsActivity(int productId) {
        //TODO: implement
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(KEY_PRODUCT_ID, productId);
        startActivity(intent);
    }

    //TODO: .gitignore apply, and find hove to remove changes history and unneeded file committed early.
}
