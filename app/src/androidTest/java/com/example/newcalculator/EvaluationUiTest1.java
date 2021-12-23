package com.example.newcalculator;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.newcalculator.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EvaluationUiTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void evaluationUiTest1() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.five_btn), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        2),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.add_btn), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        3),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.seven_btn), withText("7"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        1),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.multiply_btn), withText("?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        1),
                                3),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.sqrt_btn), withText("?"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        0),
                                2),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.one_btn), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        3),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.four_btn), withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        2),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.five_btn), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        2),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.equals_btn), withText("="),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayout),
                                        4),
                                3),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tvResult), withText("89.291"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("89.291")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
