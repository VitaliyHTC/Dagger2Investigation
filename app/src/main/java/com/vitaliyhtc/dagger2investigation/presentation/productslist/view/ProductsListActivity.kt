package com.vitaliyhtc.dagger2investigation.presentation.productslist.view

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vitaliyhtc.dagger2investigation.R
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter.ProductsListPresenter
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.ProductsListAdapter
import com.vitaliyhtc.dagger2investigation.utils.toastLong
import kotlinx.android.synthetic.main.activity_products_list.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ProductsListActivity : BaseActivity(), ProductsListView {

    @Inject
    internal lateinit var mPresenterProvider: Provider<ProductsListPresenter>

    @InjectPresenter
    internal lateinit var mProductsListPresenter: ProductsListPresenter

    @ProvidePresenter
    internal fun provideProductsListPresenter() = mPresenterProvider.get()

    private lateinit var mProductsListAdapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate: called")

        setContentView(R.layout.activity_products_list)

        initViews()

        loadData()
    }

    private fun initViews() {
        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        mProductsListAdapter = ProductsListAdapter()
        mProductsListAdapter.setOnProductClickListener { productId -> mProductsListPresenter.onProductClick(productId) }
        rv_recyclerView.adapter = mProductsListAdapter
    }

    private fun loadData() {
        mProductsListPresenter.loadData()
    }

    override fun addProductsToResult(products: List<Product>) {
        mProductsListAdapter.appendToProducts(products)

        lifecycle.addObserver(object: LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
                Timber.e("event: $event")
            }
        })

        //mProductsListPresenter.subscribeForProductsUpdates(this)
        mProductsListPresenter.subscribeForProductsUpdates()
    }

    override fun updateProductsResult(products: List<Product>) {
        mProductsListAdapter.updateProducts(products)
    }

    override fun loadProductsError(t: Throwable) {
        toastLong("ERROR: ${t.message}")
    }

    override fun launchProductDetailsActivity(productId: Int) {
        ProductDetailsActivity.newInstance(this, productId)
    }

    override fun showLoadingInProgress() {
        pb_progressbar.visibility = View.VISIBLE
    }

    override fun hideLoadingInProgress() {
        pb_progressbar.visibility = View.GONE
    }
}