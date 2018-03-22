package com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class ProductsListPresenter(val productRepository: ProductRepository) : MvpPresenter<ProductsListView>() {

    private val LCBO_FIRST_PAGE_INDEX = 0x01
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var isDataLoaded: Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }

    fun loadData() {
        Timber.d("loadData: called")
        if (!isDataLoaded) loadProducts(LCBO_FIRST_PAGE_INDEX)
    }

    private fun loadProducts(page: Int) {
        isDataLoaded = true
        viewState.showLoadingInProgress()
        productRepository.getProducts(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { d: Disposable -> mCompositeDisposable.add(d) }
                .subscribe(
                        this::addProductsToResult,
                        this::loadProductsError
                )
    }

    private fun addProductsToResult(products: List<Product>) {
        viewState.hideLoadingInProgress()
        viewState.addProductsToResult(products)
    }

    private fun loadProductsError(throwable: Throwable) {
        Timber.e(RuntimeException(throwable))
        viewState.hideLoadingInProgress()
        viewState.loadProductsError(throwable)
    }

    fun onProductClick(productId: Int) {
        viewState.launchProductDetailsActivity(productId)
    }
}