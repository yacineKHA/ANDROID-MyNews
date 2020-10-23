package com.projet5.mynewsreprog;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NotificationActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(NotificationActivity.class);

    @Test
    public void test(){
        onView(withId(R.id.search_textN)).perform(ViewActions.typeText("football")).check(matches(withText("football")));
        onView(withId(R.id.checkbox_sports)).perform(click()).check(matches(isChecked()));
        closeSoftKeyboard();
        onView(withId(R.id.switchButton)).perform(click());
    }
}
