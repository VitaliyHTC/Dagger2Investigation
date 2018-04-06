package com.vitaliyhtc.dagger2investigation

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter.ProductsListAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTest1 {

    @get:Rule
    val mProductsListActivityTestRule: ActivityTestRule<ProductsListActivity> =
            ActivityTestRule(ProductsListActivity::class.java, true, true)

    @get:Rule
    val mAsyncTaskSchedulerRule: AsyncTaskSchedulerRule = AsyncTaskSchedulerRule()

    // TODO: check AccessibilityValidator.enable()
    @Before
    fun initTestConditions() {
        getTargetContext()
    }

    /**
     * я переглянув трох
    в принципі все нормуль, тільки
    1 - юніт тести поламав
    2 - назви змінних з префіксом і без. Вже краще юзати однаково без префікса
    3 - назви методів в тестах. Приклад - whenClickOnLogin_ThenLogin (Дія_Результат)
     */

    @Test
    fun someTest() {
        Espresso.onView(withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<ProductsListAdapter.ProductsListViewHolder>(38))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(38))
                //.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
                //.perform(RecyclerViewActions.scrollTo<ProductsListAdapter.ProductsListViewHolder>(withText("Heineken")))
                //.perform(click())
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        hasDescendant(withText("Laker Ice")), click()))

        Espresso.onView(withId(R.id.cb_favorite)).perform(click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_recyclerView))
                .perform(RecyclerViewActions.actionOnItem<ProductsListAdapter.ProductsListViewHolder>(
                        hasDescendant(withText("Laker Ice")), click()))
        Espresso.onView(withId(R.id.cb_favorite)).check(matches(isChecked()))
        Espresso.pressBack()
    }

    @After
    fun cleanup() {
    }
}