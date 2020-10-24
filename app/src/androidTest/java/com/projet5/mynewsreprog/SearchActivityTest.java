package com.projet5.mynewsreprog;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(SearchActivity.class);

    @Test
    public void test(){

        onView(withId(R.id.search_text)).perform(typeText("google")).check(matches(withText("google"))).perform(closeSoftKeyboard());
        onView(withId(R.id.checkbox_business)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void checkboxesTest(){

        onView(withId(R.id.checkbox_business)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.checkbox_arts)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.checkbox_Entrepreneurs)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.checkbox_politics)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.checkbox_sports)).perform(click()).check(ViewAssertions.matches(isChecked()));
        onView(withId(R.id.checkbox_travel)).perform(click()).check(ViewAssertions.matches(isChecked()));
    }
}
