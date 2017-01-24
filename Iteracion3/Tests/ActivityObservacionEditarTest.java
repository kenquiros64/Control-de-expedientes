package com.example.ediloaz.control07;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityObservacionEditarTest {

    @Rule
    public ActivityTestRule<ActivityLogin> mActivityTestRule = new ActivityTestRule<>(ActivityLogin.class);

    @Test
    public void activityObservacionEditarTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("admin@admin.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText2.perform(replaceText("adminexpctlr"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Iniciar sesión"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dashboard_Pacientes), withText("Pacientes")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction button = onView(
                withText("Mostrar"));
        button.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_PacienteObservaciones), withText("Observaciones"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction button2 = onView(
                allOf(withText("Editar"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_ObservacionRegistrar_description), withText("Observación "), isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edit_ObservacionRegistrar_description), withText("Observación "), isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.edit_ObservacionRegistrar_description), withText("Observación "), isDisplayed()));
        appCompatEditText5.perform(replaceText("Observación actualizada "), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button_ObservacionesNuevo_editar), withText("Editar")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_Observaciones_list), withText("Observaciones de paciente"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Observaciones de paciente")));

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
