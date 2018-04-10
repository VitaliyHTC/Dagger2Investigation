package com.vitaliyhtc.dagger2investigation

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity.Companion.KEY_PRODUCT_ID
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.ProductsListAdapter
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTest1 {

    private val mLakerIceId: Int = 142620

    @get:Rule
    val mProductsListActivityTestRule: ActivityTestRule<ProductsListActivity> =
            ActivityTestRule(ProductsListActivity::class.java, true, true)

    @get:Rule
    val mAsyncTaskSchedulerRule: AsyncTaskSchedulerRule = AsyncTaskSchedulerRule()

    // TODO: check AccessibilityValidator.enable()
    @Before
    fun initTestConditions() {
    }

    @Test
    fun someTest() {
        val productName = "Laker Ice"

        Espresso.onView(withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<ProductsListAdapter.ProductsListViewHolder>(38))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(38))
                //.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
                //.perform(RecyclerViewActions.scrollTo<ProductsListAdapter.ProductsListViewHolder>(withText("Heineken")))
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        hasDescendant(withText(productName)), click()))

        Espresso.onView(withId(R.id.cb_favorite)).perform(click())
        //intending(hasExtra(KEY_PRODUCT_ID, mLakerIceId))
        Espresso.onView(withId(R.id.product_value_name)).check(matches(withText(productName)))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        hasDescendant(withText(productName)), click()))
        Espresso.onView(withId(R.id.cb_favorite)).check(matches(isChecked()))
        Espresso.pressBack()
    }

    @Test
    fun testOutgoingIntent() {
        val productName = "Laker Ice"

        Intents.init()
        Espresso.onView(ViewMatchers.withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<ProductsListAdapter.ProductsListViewHolder>(38))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(38))
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText(productName)), ViewActions.click()))

        Intents.intended(IntentMatchers.hasComponent(ProductDetailsActivity::class.java.name))
        Intents.intended(hasExtra(Matchers.equalTo(KEY_PRODUCT_ID), Matchers.equalTo(mLakerIceId)))
        Intents.release()
    }

    @After
    fun cleanup() {
    }
}