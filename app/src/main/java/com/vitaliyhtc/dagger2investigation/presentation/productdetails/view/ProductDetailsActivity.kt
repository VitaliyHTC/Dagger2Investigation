package com.vitaliyhtc.dagger2investigation.presentation.productdetails.view

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vitaliyhtc.dagger2investigation.Config.KEY_PRODUCT_ID
import com.vitaliyhtc.dagger2investigation.Config.NO_VALUE_INT
import com.vitaliyhtc.dagger2investigation.R
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter.ProductDetailsPresenter
import com.vitaliyhtc.dagger2investigation.utils.toastShort
import kotlinx.android.synthetic.main.activity_product_details.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ProductDetailsActivity : BaseActivity(), ProductDetailsView {

    // TODO: 3/23/18 check val/var everywhere
    private var targetProductId = NO_VALUE_INT

    @Inject
    internal lateinit var mPresenterProvider: Provider<ProductDetailsPresenter>

    @InjectPresenter
    internal lateinit var mProductDetailsPresenter: ProductDetailsPresenter

    @ProvidePresenter
    internal fun provideProductDetailsPresenter() = mPresenterProvider.get() // much easier =)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate: called")

        setContentView(R.layout.activity_product_details)
        targetProductId = intent.getIntExtra(KEY_PRODUCT_ID, NO_VALUE_INT)

        loadProduct(targetProductId)
    }

    private fun loadProduct(productId: Int) {
        mProductDetailsPresenter.loadProduct(productId)
    }

    override fun showProduct(product: Product) {
        Picasso.with(applicationContext)
                .load(product.image_url)
                .placeholder(R.drawable.ic_list_item_bg)
                .error(R.drawable.ic_broken_image)
                .into(image_view_product_big)

        product_value_name.text = product.name
        product_value_tags.text = product.tags
        product_value_id.text = product.id.toString()
        product_value_price_in_cents.text = product.price_in_cents.toString()
        product_value_regular_price_in_cents.text = product.regular_price_in_cents.toString()
    }

    override fun loadProductsError(t: Throwable) {
        toastShort("ERROR: ${t.message}")
    }
}