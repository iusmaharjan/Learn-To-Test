package com.ayushmaharjan.learning.learntotest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Build;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ayushmaharjan.learning.learntotest.intenttest.ContactsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<MainActivity>(MainActivity.class);

    private static String PACKAGE_ANDROID_DIALER = "com.android.phone";

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Starting with Android Lollipop the dialer package has changed.
            PACKAGE_ANDROID_DIALER = "com.android.server.telecom";
        }
    }

    private static final String PHONE_NUMBER = "9808269307";

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void validateIntentSentToPackage() {

        //Type phone number to be dialed
        onView(withId(R.id.editText)).perform(typeText(PHONE_NUMBER), closeSoftKeyboard());

        //Click on launchDialerButton
        onView(withId(R.id.launchDialerButton)).perform(click());

        //Verify that an intent was sent successfully and with correct action
        Intents.intended(allOf(IntentMatchers.toPackage(PACKAGE_ANDROID_DIALER),
                IntentMatchers.hasAction(Intent.ACTION_CALL)));

    }

    @Test
    public void pickContact_click_DisplaysNum() {

        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
        // is never launched and result is stubbed.
        Intents.intending(hasComponent(hasShortClassName(".ContactsActivity"))).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, ContactsActivity.createResultData(PHONE_NUMBER)));

        // Click the pick contact button.
        onView(withId(R.id.pickContact)).perform(click());

        // Check that the number is displayed in the UI.
        onView(withId(R.id.editText))
                .check(matches(withText(PHONE_NUMBER)));
    }
}
