package com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProductDetailsPresenterTest {

    @Mock
    private lateinit var mProductsRepository: ProductRepository

    @Mock
    private lateinit var mViewState: `ProductDetailsView$$State`

    private lateinit var mPresenter: ProductDetailsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        // TODO: 3/22/18 you can skip params if you don't need them,
        // '_' is used if you have many paramaters and you don't care about one of them
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        mPresenter = ProductDetailsPresenter(mProductsRepository)
        mPresenter.setViewState(mViewState)
    }

    @Test
    fun onLoadProduct_Valid_ShowProduct() {
        val product: Product = mock(Product::class.java)
        val productId = 128

        // TODO: 3/22/18 try mockito for kotlin library
        `when`(mProductsRepository.getOneProduct(productId)).thenReturn(Single.just(product))

        mPresenter.loadProduct(productId)
        verify(mViewState).showProduct(product)
    }

    @Test
    fun onLoadProduct_Fail_showError() {
        val ex = RuntimeException("Oops. Some error has occurred!")
        val productId = 128

        `when`(mProductsRepository.getOneProduct(productId)).thenReturn(Single.error(ex))

        mPresenter.loadProduct(productId)
        verify(mViewState).loadProductsError(ex)
    }

    @After
    fun cleanUp() {
        mPresenter.onDestroy()
    }
}