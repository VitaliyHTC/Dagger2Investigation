package com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter

import android.arch.lifecycle.LifecycleOwner
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
class ProductsListPresenter(private val productRepository: ProductRepository) : MvpPresenter<ProductsListView>() {

    private val LCBO_FIRST_PAGE_INDEX = 0x01
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mIsDataLoaded: Boolean = false
    private var mIsInLoadingProgress: Boolean = false

    fun loadData() {
        Timber.d("loadData: called")
        if (!mIsDataLoaded && !mIsInLoadingProgress) loadProducts(LCBO_FIRST_PAGE_INDEX)
    }

    private fun loadProducts(page: Int) {
        mIsInLoadingProgress = true

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
        mIsInLoadingProgress = false
        mIsDataLoaded = true

        viewState.hideLoadingInProgress()
        viewState.addProductsToResult(products)
    }

    private fun loadProductsError(throwable: Throwable) {
        mIsInLoadingProgress = false

        Timber.e(RuntimeException(throwable))
        viewState.hideLoadingInProgress()
        viewState.loadProductsError(throwable)
    }

    fun subscribeForProductsUpdates(lifecycleOwner: LifecycleOwner) {
        productRepository.subscribeForProductsUpdates(lifecycleOwner) {
            viewState.updateProductsResult(it)
        }
    }

    fun subscribeForProductsUpdates() {
        productRepository.subscribeForProductsUpdates { viewState.updateProductsResult(it) }
    }

    fun onProductClick(productId: Int) {
        viewState.launchProductDetailsActivity(productId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }
}