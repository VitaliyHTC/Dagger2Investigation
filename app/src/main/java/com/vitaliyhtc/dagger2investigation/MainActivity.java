package com.vitaliyhtc.dagger2investigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.vitaliyhtc.dagger2investigation.view.adapter.OnProductClickListener;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vitaliyhtc.dagger2investigation.Config.KEY_PRODUCT_ID;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;

    ProductRepository mProductRepository;

    ProductsListAdapter mProductsListAdapter;

    OnProductClickListener mOnProductClickListener =
            new OnProductClickListener() {
                @Override
                public void onProductClick(int productId) {
                    onProductClick1(productId);
                }
            };

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
        mProductsListAdapter.setOnProductClickListener(mOnProductClickListener);
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
        mMainPresenter.onProductClick(productId);
    }

    private void loadData() {
        mMainPresenter.loadData();
    }

    @Override
    public void addProductToResult(Product product) {
        mProductsListAdapter.appendToProducts(product);
        mProductsListAdapter.notifyItemInserted(mProductsListAdapter.getItemCount());
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
}
