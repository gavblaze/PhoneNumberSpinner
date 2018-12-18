package com.gavblaze.android.phonenumberspinner;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        assertEquals("com.gavblaze.android.phonenumberspinner", appContext.getPackageName());
    }

    @Test
    public void iterateSpinner() {
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.labels_array);
        for (int i = 0; i < myArray.length; i++) {
            // Find the spinner and click on it.
            onView(withId(R.id.label_spinner)).perform(click());
            // Find the spinner item and click on it.
            onData(is(myArray[i])).perform(click());
            // Find the text view and check that the spinner item
            // is part of the string.
            onView(withId(R.id.text_phonelabel)).check(matches(withText(containsString(myArray[i]))));
        }
    }
}
