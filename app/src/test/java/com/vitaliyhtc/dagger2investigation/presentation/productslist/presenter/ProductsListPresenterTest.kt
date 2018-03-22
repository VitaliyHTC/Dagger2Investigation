package com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.`ProductsListView$$State`
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


class ProductsListPresenterTest {

    @Mock
    private lateinit var mProductsRepository: ProductRepository

    @Mock
    private lateinit var mViewState: `ProductsListView$$State`

    private lateinit var mPresenter: ProductsListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        mPresenter = ProductsListPresenter(mProductsRepository)
        mPresenter.setViewState(mViewState)
    }


    @Test
    fun onLoadProducts_Valid_AddProductsToList() {
        val product1: Product = mock(Product::class.java)
        val product2: Product = mock(Product::class.java)
        val products: List<Product> = listOf(product1, product2)

        val pageNumber = 1

        `when`(mProductsRepository.getProducts(pageNumber)).thenReturn(Single.just(products))

        mPresenter.loadData()
        verify(mViewState).addProductsToResult(products)
    }

    @Test
    fun onLoadProducts_Fail_showError() {
        val ex = RuntimeException("Oops. Some error has occurred!")

        val pageNumber = 1

        `when`(mProductsRepository.getProducts(pageNumber)).thenReturn(Single.error(ex))

        mPresenter.loadData()
        verify(mViewState).loadProductsError(ex)
    }

    @Test
    fun onProductClick_LaunchProductDetailsActivity() {
        val productId = 128

        mPresenter.onProductClick(productId)
        verify(mViewState).launchProductDetailsActivity(productId)
    }

    @After
    fun cleanUp() {
        mPresenter.onDestroy()
    }
}