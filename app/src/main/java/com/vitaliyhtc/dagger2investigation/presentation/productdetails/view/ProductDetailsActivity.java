package com.vitaliyhtc.dagger2investigation.presentation.productdetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;
import com.vitaliyhtc.dagger2investigation.R;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.BaseActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter.ProductDetailsPresenter;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.vitaliyhtc.dagger2investigation.Config.NO_VALUE_INT;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsView {

    public static final String KEY_PRODUCT_ID = "KEY_PRODUCT_ID";

    private int targetProductId = NO_VALUE_INT;


    @Inject
    Provider<ProductDetailsPresenter> mPresenterProvider;

    @InjectPresenter
    ProductDetailsPresenter mProductDetailsPresenter;

    @ProvidePresenter
    ProductDetailsPresenter provideProductDetailsPresenter() {
        return mPresenterProvider.get();
    }

    @BindView(R.id.image_view_product_big)
    ImageView mProductBigImageView;

    @BindView(R.id.product_value_name)
    TextView mProductNameTextView;
    @BindView(R.id.product_value_tags)
    TextView mProductTagsTextView;
    @BindView(R.id.product_value_id)
    TextView mProductIdTextView;
    @BindView(R.id.product_value_price_in_cents)
    TextView mProductPriceTextView;
    @BindView(R.id.product_value_regular_price_in_cents)
    TextView mProductRegularPriceTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.d("onCreate: called");

        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        targetProductId = getIntent().getIntExtra(KEY_PRODUCT_ID, NO_VALUE_INT);

        loadProduct(targetProductId);
    }

    private void loadProduct(int productId) {
        mProductDetailsPresenter.loadProduct(productId);
    }

    @Override
    public void showProduct(Product product) {
        Picasso.with(getApplicationContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.ic_list_item_bg)
                .error(R.drawable.ic_broken_image)
                .into(mProductBigImageView);

        mProductNameTextView.setText(product.getName());
        mProductTagsTextView.setText(product.getTags());
        String id = "" + product.getId();
        mProductIdTextView.setText(id);
        String priceInCents = "" + product.getPriceInCents();
        mProductPriceTextView.setText(priceInCents);
        String regularPriceInCents = "" + product.getRegularPriceInCents();
        mProductRegularPriceTextView.setText(regularPriceInCents);
    }

    @Override
    public void loadProductsError(Throwable t) {
        Toast.makeText(this, "ERROR: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

}
