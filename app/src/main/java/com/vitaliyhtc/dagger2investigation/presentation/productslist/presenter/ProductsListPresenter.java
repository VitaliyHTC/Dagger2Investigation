package com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.RxFilter;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.mvp.BasePresenter;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ProductsListPresenter implements BasePresenter<ProductsListView> {

    private static final int LCBO_FIRST_PAGE_INDEX = 0x01;
    private static final RxFilter<Product> PRODUCTS_FILTER = product -> true;

    private ProductsListView mProductsListView;

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    // TODO: 1/19/18 why  @Inject annotation here? check the documentation about constructor injection and module provide
    @Inject
    public ProductsListPresenter(ProductRepository productRepository) {
        // TODO: 1/19/18 why Timber.e?
        Timber.e("ProductsListPresenter: injected");
        mProductRepository = productRepository;
    }

    @Override
    public void onAttachView(ProductsListView view) {
        mProductsListView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetachView() {
        mProductsListView = null;

        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void loadData() {
        Timber.e("loadData: called!");
        loadProducts(PRODUCTS_FILTER, LCBO_FIRST_PAGE_INDEX);
    }

    private void loadProducts(RxFilter<Product> filter, int page) {
        Disposable disposable =
                mProductRepository.getProductsObservable(page)
                        .subscribeOn(Schedulers.io())
                        .flatMap(Observable::fromIterable)
                        .filter(filter::isMeetsCondition)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(
                                products -> addProductToResult(products),
                                this::loadProductsError
                        );
        mCompositeDisposable.add(disposable);
    }

    private void addProductToResult(List<Product> products) {
        if (mProductsListView != null) mProductsListView.addProductsToResult(products);
    }

    private void loadProductsError(Throwable t) {
        if (mProductsListView != null) mProductsListView.loadProductsError(t);
    }

    public void onProductClick(int productId) {
        if (mProductsListView != null) mProductsListView.launchProductDetailsActivity(productId);
    }
}
