package com.vitaliyhtc.dagger2investigation.presentation.productslist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.vitaliyhtc.dagger2investigation.R;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter.ProductsListPresenter;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.ProductsListAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity.KEY_PRODUCT_ID;

public class ProductsListActivity extends BaseActivity implements ProductsListView {
    private static final String TAG = "ProductsListActivity";

    @Inject
    Provider<ProductsListPresenter> mPresenterProvider;

    @InjectPresenter
    ProductsListPresenter mProductsListPresenter;

    @ProvidePresenter
    ProductsListPresenter provideProductsListPresenter() {
        return mPresenterProvider.get();
    }


    private ProductsListAdapter mProductsListAdapter;


    @BindView(R.id.rv_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.d("onCreate: called");

        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);

        initViews();

        loadData();
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsListAdapter = new ProductsListAdapter();
        mProductsListAdapter.setOnProductClickListener(this::onProductClick);
        mRecyclerView.setAdapter(mProductsListAdapter);
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
        Log.e(TAG, "Error", t);
        Toast.makeText(this, "ERROR: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchProductDetailsActivity(int productId) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(KEY_PRODUCT_ID, productId);
        startActivity(intent);
    }
}
