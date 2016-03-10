package com.ayushmaharjan.learning.learntotest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ayushmaharjan.learning.learntotest.CustomActions.setTextInTextView;
import static com.ayushmaharjan.learning.learntotest.CustomMatchers.hasMinimumTextSize;
import static com.ayushmaharjan.learning.learntotest.CustomMatchers.withButtonText;
import static com.ayushmaharjan.learning.learntotest.CustomMatchers.withStatusText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void sayHello(){

        //Type "Peter" into the edit text
        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        //Click on 'Say hello!' button
        onView(withText("Say hello!")).perform(click());

        //Expected text is "Hello, Peter!"
        String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";

        //Custom ViewMatcher withStatusText() in action
        onView(withId(R.id.textView)).check(matches(withStatusText(expectedText)));

        //Custom ViewMatcher withButtonText() in action
        onView(withId(R.id.sayHelloButton)).check(matches(withButtonText("Say hello!")));

        //Works as well because Button extends TextView
        onView(withId(R.id.sayHelloButton)).check(matches(withStatusText("Say hello!")));

        //This test fails because matcher does not match the selected view
        //onView(withId(R.id.textView)).check(matches(withButtonText(expectedText)));

    }

    //This test should fail because of AmbiguousViewMatcherException between Toolbar and TextView
    @Ignore
    @Test
    public void findByText() {
        onView(withText("Learn to Test")).perform(typeText("Hi"));
    }

    @Test
    public void checkIfTextViewCanBeModified() {
        String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";

        //Error when trying to alter text using replaceText/typeText
        //onView(withId(R.id.textView)).perform(replaceText(expectedText));

        //Custom ViewAction setTextInTextView() created to update text in text view
        onView(withId(R.id.textView)).perform(setTextInTextView(expectedText)).check(matches(withText(expectedText)));
    }

    @Test
    public void testStringSize() {
        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        //Testing Custom Matcher for ViewMatcher.withText(final Matcher<String> stringMatcher)
        onView(withId(R.id.textView)).check(matches(withText(hasMinimumTextSize(5))));

        // Failing case
        //onView(withId(R.id.textView)).check(matches(withText(hasMinimumTextSize(50))));
    }

}