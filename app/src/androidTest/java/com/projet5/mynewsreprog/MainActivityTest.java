package com.projet5.mynewsreprog;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void tabLayoutAndRecyclerViewTest() {

        ViewInteraction tabview = onView(allOf(withParent(withParent(withId(R.id.tablayout))), withContentDescription("Business")));
        tabview.perform(click());

        threadSleep();

        ViewInteraction viewRecyclerView3 = onView(withId(R.id.recyclerview3));
        viewRecyclerView3.perform(ViewActions.swipeUp());

        threadSleep();

        ViewInteraction tabview2 = onView(allOf(withParent(withParent(withId(R.id.tablayout))), withContentDescription("Top stories")));
        tabview2.perform(click());

        threadSleep();

        ViewInteraction viewRecyclerView2 = onView(withId(R.id.recyclerview2));
        viewRecyclerView2.perform(ViewActions.swipeUp());

        threadSleep();

        ViewInteraction tabview3 = onView(allOf(withParent(withParent(withId(R.id.tablayout))), withContentDescription("Most viewed")));
        tabview3.perform(click());

        threadSleep();

        ViewInteraction viewRecyclerView1 = onView(withId(R.id.recyclerview_1));
        viewRecyclerView1.perform(ViewActions.swipeUp());

    }

    @Test
    public void recyclerViewClick(){
        onView(allOf(withParent(withParent(withId(R.id.tablayout))), withContentDescription("Top stories"))).perform(click());
        threadSleep();
        onView(withId(R.id.recyclerview2)).perform(ViewActions.click());
    }

    @Test
    public void searchButtonClick(){
        onView(withId(R.id.search)).perform(click());
    }

    @Test
    public void onMenuClick(){

        onView(allOf(withContentDescription("More options"), withParent(withParent(withId(R.id.toolbar))))).perform(click());
        onView(allOf(withId(R.id.title), withText("Notifications"), withParent(withParent(withId(R.id.content))))).perform(click());

    }

    private void threadSleep() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
