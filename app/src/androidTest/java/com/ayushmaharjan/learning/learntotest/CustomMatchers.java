package com.ayushmaharjan.learning.learntotest;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

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
}
