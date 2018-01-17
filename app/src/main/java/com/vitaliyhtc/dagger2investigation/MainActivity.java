package com.vitaliyhtc.dagger2investigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vitaliyhtc.dagger2investigation.app.MainApplication;
import com.vitaliyhtc.dagger2investigation.di.component.DaggerMainActivityComponent;
import com.vitaliyhtc.dagger2investigation.di.component.MainActivityComponent;
import com.vitaliyhtc.dagger2investigation.di.module.MainActivityModule;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presenter.MainPresenter;
import com.vitaliyhtc.dagger2investigation.view.MainView;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;

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
        mMainPresenter = new MainPresenter(mProductRepository);
        mMainPresenter.onAttachView(this);

        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDetachView();
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter() {
        // TODO: 1/17/18 check the lambda simplification
        mProductsListAdapter.setOnProductClickListener(this::onProductClick1);
        mRecyclerView.setAdapter(mProductsListAdapter);
    }

    private void initDi() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .productDataComponent(MainApplication.get(this).getProductDataComponent())
                .build();
        mProductRepository = mainActivityComponent.getProductRepository();
        mProductsListAdapter = mainActivityComponent.getProductsListAdapter();
    }

    private void onProductClick1(int productId) {
        //TODO: implement
    }

    private void loadData() {
        mMainPresenter.loadData();
    }

    @Override
    public void addProductToResult(Product product) {
        // TODO: 1/17/18 all work here related to the adapter, why don't encapsulate this with single method?
        mProductsListAdapter.appendToProducts(product);
        mProductsListAdapter.notifyItemInserted(mProductsListAdapter.getItemCount());
    }

    @Override
    public void loadProductsError(Throwable t) {
        Toast.makeText(this, "ERROR: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
