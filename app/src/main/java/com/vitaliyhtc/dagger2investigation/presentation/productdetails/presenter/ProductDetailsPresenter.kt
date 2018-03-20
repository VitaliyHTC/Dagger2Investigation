package com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class ProductDetailsPresenter(val productRepository: ProductRepository) : MvpPresenter<ProductDetailsView>() {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var isDataLoaded: Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }

    public fun loadProduct(productId: Int) {
        if (isDataLoaded) return

        isDataLoaded = true

        productRepository.getOneProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { d: Disposable -> mCompositeDisposable.add(d) }
                .subscribe(this::onProductLoaded, this::loadProductsError)
    }

    private fun onProductLoaded(product: Product) {
        viewState.showProduct(product)
    }

    private fun loadProductsError(throwable: Throwable) {
        Timber.e(throwable)
        viewState.loadProductsError(throwable)
    }

}