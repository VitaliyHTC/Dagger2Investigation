package com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ProductDetailsPresenter extends MvpPresenter<ProductDetailsView> {

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    private boolean isDataLoaded;

    public ProductDetailsPresenter(ProductRepository productRepository) {
        Timber.d("ProductDetailsPresenter: injected");
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void loadProduct(int productId) {
        if (isDataLoaded) return;

        isDataLoaded = true;

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
        getViewState().showProduct(product);
    }

    private void loadProductsError(Throwable t) {
        getViewState().loadProductsError(t);
    }

}
