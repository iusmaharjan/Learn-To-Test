package com.ayushmaharjan.learning.learntotest;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import timber.log.Timber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ayushmaharjan.learning.learntotest.CustomMatchers.doesContainInfoText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IdlingActivityTest {

    @Rule
    public ActivityTestRule<IdlingActivity> activityTestRule = new ActivityTestRule<>(IdlingActivity.class);

    @Test
    public void someIdlingTest() {
        onView(withId(R.id.btn_load)).perform(click());
        IdlingResource idlingResource = new SomeIdlingResource(10000);
        registerIdlingResources(idlingResource);
        onView(withId(R.id.info_txt)).check(matches(doesContainInfoText()));
        unregisterIdlingResources(idlingResource);
    }

    public class SomeIdlingResource implements IdlingResource {

        private final long startTime;
        private final long waitingTime;
        private ResourceCallback resourceCallback;

        public SomeIdlingResource(long waitingTime) {
            startTime = System.currentTimeMillis();
            this.waitingTime = waitingTime;
        }

        @Override
        public String getName() {
            return "SomeIdlingResource";
        }

        @Override
        public boolean isIdleNow() {
            long elapsedTime = System.currentTimeMillis() - startTime;
            Timber.d("ElapsedTime =%d", elapsedTime);
            boolean isIdle = elapsedTime >= waitingTime;
            Timber.d("Should Espresso be idle =%b", isIdle);

            if(isIdle)
                resourceCallback.onTransitionToIdle();
            return isIdle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            Timber.d("Register To Transistion callback called by Espresso");
            resourceCallback = callback;
        }
    }
}
