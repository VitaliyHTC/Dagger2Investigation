package com.vitaliyhtc.dagger2investigation

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.ProductsListAdapter
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@Ignore
class UiTest {

    @get:Rule
    val mProductsListActivityTestRule: ActivityTestRule<ProductsListActivity> =
            ActivityTestRule(ProductsListActivity::class.java, true, true)


    @Before
    fun initTestConditions() {
    }

    @Test
    fun someTest() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<ProductsListAdapter.ProductsListViewHolder>(38))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(38))
                //.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
                //.perform(RecyclerViewActions.scrollTo<ProductsListAdapter.ProductsListViewHolder>(withText("Heineken")))
                //.perform(click())
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        hasDescendant(withText("Laker Ice")), click()))

    }

    @After
    fun cleanup() {
    }
}