package com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.mvp.BasePresenter;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ProductDetailsPresenter implements BasePresenter<ProductDetailsView> {

    private ProductDetailsView mProductDetailsView;

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    public ProductDetailsPresenter(ProductRepository productRepository) {
        Timber.d("ProductDetailsPresenter: injected");
        mProductRepository = productRepository;
    }

    @Override
    public void onAttachView(ProductDetailsView view) {
        mProductDetailsView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetachView() {
        mProductDetailsView = null;

        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void loadProduct(int productId) {
        Disposable disposable =
                mProductRepository.getOneProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        product -> onProductLoaded(product),
                        this::loadProductsError
                );
        mCompositeDisposable.add(disposable);
    }

    private void onProductLoaded(Product product) {
        mProductDetailsView.showProduct(product);
    }

    private void loadProductsError(Throwable t) {
        mProductDetailsView.loadProductsError(t);
    }

}
