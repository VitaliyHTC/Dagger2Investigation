package com.vitaliyhtc.dagger2investigation.presentation.productslist.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vitaliyhtc.dagger2investigation.Config.KEY_PRODUCT_ID
import com.vitaliyhtc.dagger2investigation.R
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter.ProductsListPresenter
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.OnProductClickListener
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
    internal fun provideProductsListPresenter(): ProductsListPresenter {
        return mPresenterProvider.get()
    }

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
        mProductsListAdapter.setOnProductClickListener(object : OnProductClickListener {
            override fun onProductClick(productId: Int) {
                mProductsListPresenter.onProductClick(productId)
            }
        })
        rv_recyclerView.adapter = mProductsListAdapter
    }

    private fun loadData() {
        mProductsListPresenter.loadData()
    }

    override fun addProductsToResult(products: List<Product>) {
        mProductsListAdapter.appendToProducts(products)
    }

    override fun loadProductsError(t: Throwable) {
        toastLong("ERROR: ${t.message}")
    }

    override fun launchProductDetailsActivity(productId: Int) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(KEY_PRODUCT_ID, productId)
        startActivity(intent)
    }
}