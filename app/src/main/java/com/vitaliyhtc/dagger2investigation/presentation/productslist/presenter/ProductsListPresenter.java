package com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ProductsListPresenter extends MvpPresenter<ProductsListView> {

    private static final int LCBO_FIRST_PAGE_INDEX = 0x01;

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    private boolean isDataLoaded;

    public ProductsListPresenter(ProductRepository productRepository) {
        Timber.d("ProductsListPresenter: injected");
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

    public void loadData() {
        Timber.d("loadData: called!");
        if (!isDataLoaded) loadProducts(LCBO_FIRST_PAGE_INDEX);
    }

    private void loadProducts(int page) {
        isDataLoaded = true;
        Disposable disposable =
                mProductRepository.getProducts(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::addProductToResult,
                                this::loadProductsError
                        );
        mCompositeDisposable.add(disposable);
    }

    private void addProductToResult(List<Product> products) {
        getViewState().addProductsToResult(products);
    }

    private void loadProductsError(Throwable t) {
        getViewState().loadProductsError(t);
    }

    public void onProductClick(int productId) {
        getViewState().launchProductDetailsActivity(productId);
    }
}
