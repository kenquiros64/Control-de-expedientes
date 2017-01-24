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
public class ActivityNuevoPacienteTest {

    @Rule
    public ActivityTestRule<ActivityLogin> mActivityTestRule = new ActivityTestRule<>(ActivityLogin.class);

    @Test
    public void activityNuevoPacienteTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("admin@admin.com"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText.perform(replaceText("adminexpctlr"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Iniciar sesión"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dashboard_Pacientes), withText("Pacientes")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_Pacientes_Nuevo), withText("Nuevo")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_RegistrarPaciente_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("Paciente"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_RegistrarPaciente_lastname1), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ap"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edit_RegistrarPaciente_lastname2), isDisplayed()));
        appCompatEditText4.perform(replaceText("Ap"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.edit_RegistrarPaciente_ced_number));
        appCompatEditText5.perform(scrollTo(), replaceText("4-4444-4444"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                withId(R.id.spinner_RegistrarPaciente_nacionality));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Costarricense"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                withId(R.id.spinner_RegistrarPaciente_gender));
        appCompatSpinner2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Masculino"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button_RegistrarPaciente_birth), withText("Seleccionar fecha")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Aceptar"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        pressBack();

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.edit_RegistrarPaciente_email), isDisplayed()));
        appCompatEditText6.perform(replaceText("kenquiros64@gmail.co"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button_RegistrarPaciente_registrar), withText("Registrar")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_Pacientes_list), withText("Lista de pacientes"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Lista de pacientes")));

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
