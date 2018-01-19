package com.vitaliyhtc.dagger2investigation.presentation.productslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.ProductDetailsActivity;
import com.vitaliyhtc.dagger2investigation.R;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.adapter.ProductsListAdapter;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter.ProductsListPresenter;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.vitaliyhtc.dagger2investigation.Config.KEY_PRODUCT_ID;

public class ProductsListActivity extends BaseActivity implements ProductsListView {

    @Inject
    ProductsListPresenter mProductsListPresenter;


    private ProductsListAdapter mProductsListAdapter;


    @BindView(R.id.rv_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.e("onCreate: called");

        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);

        initViews();

        setAdapter();
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
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(KEY_PRODUCT_ID, productId);
        startActivity(intent);
    }
}
