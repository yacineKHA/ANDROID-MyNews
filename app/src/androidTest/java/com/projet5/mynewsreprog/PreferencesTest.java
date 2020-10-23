package com.projet5.mynewsreprog;

import android.content.Context;
import android.view.View;
import android.widget.ListView;


import androidx.annotation.ContentView;
import androidx.test.espresso.ViewFinder;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.material.navigation.NavigationView;
import com.projet5.mynewsreprog.ApiBusiness.Fragment3;
import com.projet5.mynewsreprog.ApiTopStories.AdapterTopStories;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PreferencesTest {

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    Preferences pref = new Preferences(appContext);

    @Test
    public void preferences(){

        pref.saveBoolean(true);
        pref.savePreferences("test", "text");

        assertEquals(true, pref.loadBoolean());
        assertEquals("text", pref.loadPreferences("test"));
    }
}
