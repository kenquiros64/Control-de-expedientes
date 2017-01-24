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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityAgregarCitaTest {

    @Rule
    public ActivityTestRule<ActivityLogin> mActivityTestRule = new ActivityTestRule<>(ActivityLogin.class);

    @Test
    public void activityAgregarCitaTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("admin@admin.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText.perform(replaceText("adminexpctlr"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Iniciar sesión"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dashboard_Citas), withText("Citas")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_Citas_Nuevo), withText("Agregar cita")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.edit_CitasNuevo_Buscar));
        appCompatEditText2.perform(scrollTo(), replaceText("k"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button_CitasNuevo_Buscar), withText("Buscar")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction button = onView(
                withText("Mostrar citas"));
        button.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.button_CitasListado_Nuevo), withText("Agregar cita")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.edit_CitasNuevo_time));
        appCompatEditText3.perform(scrollTo(), replaceText("12:56"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.button_CitasNuevo2_date), withText("Seleccionar fecha")));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button1), withText("Aceptar"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton8.perform(click());

        pressBack();

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.button_CitasNuevo2_Agregar), withText("Registrar")));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_Enfermedades_list), withText("Lista de citas"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Lista de citas")));

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
