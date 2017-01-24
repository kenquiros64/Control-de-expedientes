package com.example.ediloaz.control07;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.example.ediloaz.control07.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityNuevoMedicoTest {

    @Rule
    public ActivityTestRule<ActivityWelcome> mActivityTestRule = new ActivityTestRule<>(ActivityWelcome.class);

    @Test
    public void activityNuevoMedicoTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
withId(R.id.email));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("admin@admin.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
withId(R.id.password));
        appCompatEditText.perform(scrollTo(), replaceText("adminexpctlr"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
allOf(withId(R.id.email_sign_in_button), withText("Iniciar sesión"),
withParent(allOf(withId(R.id.email_login_form),
withParent(withId(R.id.login_form))))));
        appCompatButton.perform(scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
allOf(withId(R.id.dashboard_Medicos), withText("Médicos")));
        appCompatButton2.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
allOf(withId(R.id.button_Medicos_Nuevo), withText("Nuevo")));
        appCompatButton3.perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.edit_RegistrarMedico_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("Medico"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.edit_RegistrarMedico_lastname1), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ap1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.edit_RegistarMedico_lastname2), isDisplayed()));
        appCompatEditText4.perform(replaceText("Ap2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
withId(R.id.edit_RegistarMedico_ced_number));
        appCompatEditText5.perform(scrollTo(), click());
        appCompatEditText5.perform(scrollTo(), replaceText("1-1235-1234"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
withId(R.id.edit_RegistarMedico_cod_number));
        appCompatEditText6.perform(scrollTo(), click());
        appCompatEditText6.perform(scrollTo(), replaceText("ME-1"), closeSoftKeyboard());


        ViewInteraction appCompatSpinner = onView(
withId(R.id.spinner_nacionalidad));
        appCompatSpinner.perform(scrollTo(), click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatCheckedTextView = onView(
allOf(withId(android.R.id.text1), withText("Costarricense naturalizado"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ViewInteraction appCompatEditText8 = onView(
allOf(withId(2131558607), isDisplayed())).perform(scrollTo());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appCompatEditText8.perform(scrollTo(),replaceText("medico@med.com"), closeSoftKeyboard());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText9 = onView(
allOf(withId(R.id.edit_RegistarMedico_password), isDisplayed()));
        appCompatEditText9.perform(scrollTo());
        appCompatEditText8.perform(scrollTo());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appCompatEditText9.perform(replaceText("asdasd"), closeSoftKeyboard());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton4 = onView(
allOf(withId(R.id.button_RegistarMedico_registrar), withText("Registrar")));
        appCompatButton4.perform(scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction tableRow = onView(
allOf(childAtPosition(
allOf(withId(R.id.table_Medicos_list),
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
0)),
6),
isDisplayed()));
        tableRow.check(matches(isDisplayed()));

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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
