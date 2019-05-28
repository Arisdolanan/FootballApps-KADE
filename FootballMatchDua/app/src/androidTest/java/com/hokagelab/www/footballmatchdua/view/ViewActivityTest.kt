package com.hokagelab.www.footballmatchdua.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.hokagelab.www.footballmatchdua.MainActivity
import com.hokagelab.www.footballmatchdua.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour(){
        //Aplikasi Terbuka
        onView(withId(R.id.lastRecycler))
            .check(matches(isDisplayed()))
        Thread.sleep(5000)

        // Scroll last sampai posisi akhir
        onView(withId(R.id.lastRecycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)

        // Scroll last sampai posisi awal
        onView(withId(R.id.lastRecycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)

        // klik recycler view last match posisi 0
        onView(withId(R.id.lastRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(3000)

        // posisi pada menu favorit
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.fav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //melakukan klik pada tombol favorit muncul snack bar “added to favorite”
        onView(withId(R.id.fav)).perform(click())

        //kembali pada menu sebelumnya
        Thread.sleep(3000)
        pressBack()

        //sedang di menu last
        onView(withId(R.id.lastRecycler))
            .check(matches(isDisplayed()))
        Thread.sleep(8000)

        // swipeleft menuju menu favorit namun melewati next match
        onView(withId(R.id.viewpager_main))
            .perform(swipeLeft())

        // swipeleft sudah tiba di menu favorit
        onView(withId(R.id.viewpager_main))
            .perform(swipeLeft())
        Thread.sleep(3000)

        //posisi pada menu favorit
        onView(withId(R.id.favRecycler))
            .perform(click())
            .check(matches(isDisplayed()))

        // klik recycler view favorit match posisi 0
        onView(withId(R.id.favRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(5000)

        // posisi pada menu favorit
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.fav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //melakukan klik pada tombol favorit muncul snack bar “remove to favorite”
        onView(withId(R.id.fav)).perform(click())

        //kembali pada menu sebelumnya
        Thread.sleep(3000)
        pressBack()

        //posisi pada menu favorit
        onView(withId(R.id.favRecycler))
            .check(matches(isDisplayed()))

        // swiperight menuju menu favorit namun melewati next match
        onView(withId(R.id.viewpager_main))
            .perform(swipeRight())

        // swiperight sudah tiba di menu favorit
        onView(withId(R.id.viewpager_main))
            .perform(swipeRight())
        Thread.sleep(3000)

        //posisi pada menu last
        onView(withId(R.id.lastRecycler))
            .check(matches(isDisplayed()))

        // klik recycler view last match posisi 0
        onView(withId(R.id.lastRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //kembali pada menu sebelumnya
        Thread.sleep(3000)
        pressBack()

    }
}

