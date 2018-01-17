package com.vitaliyhtc.dagger2investigation.presenter;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.domain.RxFilter;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.view.MainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.vitaliyhtc.dagger2investigation.Config.PRODUCTS_PER_PAGE;

public class MainPresenter implements BasePresenter<MainView> {

    private static final int LCBO_FIRST_PAGE_INDEX = 0x01;
    private static final RxFilter<Product> PRODUCTS_FILTER = product -> true;

    private MainView mMainView;

    private ProductRepository mProductRepository;
    private int mCountProducts;

    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
    }

    @Override
    public void onAttachView(MainView view) {
        mMainView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetachView() {
        mMainView = null;

        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void loadData() {
        loadProducts(PRODUCTS_PER_PAGE, PRODUCTS_FILTER, LCBO_FIRST_PAGE_INDEX);
    }

    private void loadProducts(int count, RxFilter<Product> filter, int page) {
        Disposable disposable =
                mProductRepository.getProductsObservable(page)
                        .subscribeOn(Schedulers.io())
                        .flatMap(Observable::fromIterable)
                        .filter(filter::isMeetsCondition)
                        .observeOn(AndroidSchedulers.mainThread())
                        // TODO: 1/17/18 collect all data and deliver everything
//                        .toList()
                        .subscribe(
                                // TODO: 1/17/18 don't write here large implementations
                                product -> onProductReceived(product, count),
                                this::loadProductsError
                        );
        mCompositeDisposable.add(disposable);
    }

    private void onProductReceived(Product product, int count) {
        if (mCountProducts < count) {
            addProductToResult(product);
            mCountProducts++;
        }
    }

    private void addProductToResult(Product product) {
        mMainView.addProductToResult(product);
    }

    private void loadProductsError(Throwable t) {
        mMainView.loadProductsError(t);
    }
}
