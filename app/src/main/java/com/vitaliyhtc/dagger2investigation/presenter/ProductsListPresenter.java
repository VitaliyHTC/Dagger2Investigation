package com.vitaliyhtc.dagger2investigation.presenter;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.RxFilter;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.view.ProductsListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductsListPresenter implements BasePresenter<ProductsListView> {

    private static final int LCBO_FIRST_PAGE_INDEX = 0x01;
    private static final RxFilter<Product> PRODUCTS_FILTER = product -> true;

    private ProductsListView mProductsListView;

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    public ProductsListPresenter(ProductRepository productRepository) {
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
        mProductsListView.addProductsToResult(products);
    }

    private void loadProductsError(Throwable t) {
        mProductsListView.loadProductsError(t);
    }

    public void onProductClick(int productId) {
        mProductsListView.launchProductDetailsActivity(productId);
    }
}
