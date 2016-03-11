package com.ayushmaharjan.learning.learntotest;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

public class CustomMatchers {

    public static Matcher<View> withStatusText(final String expected) {

        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Looking for " + expected);
            }

            @Override
            protected boolean matchesSafely(TextView item) {
                if (Pattern.matches(expected, item.getText().toString())) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static Matcher<View> withButtonText(final String expected) {

        return new BoundedMatcher<View, Button>(Button.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Looking for " + expected);
            }

            @Override
            protected boolean matchesSafely(Button item) {
                if (Pattern.matches(expected, item.getText().toString())) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static Matcher<String> hasMinimumTextSize(final int size) {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String item) {
                return item.length()>=size;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static Matcher<View> doesContainInfoText() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Matching whether text contains some value or not");

            }

            @Override
            protected boolean matchesSafely(TextView item) {
                return item.getText().toString().equals("Successfully loaded data")
                        || item.getText().toString().equals("Could not load data");
            }
        };
    }
}
