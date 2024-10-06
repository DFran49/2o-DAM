package dam.moviles.t1_proyecto03

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotActivated
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches
import kotlin.concurrent.thread

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var escenario:ActivityScenario<MainActivity>

    //se llama antes de cada test
    @Before
    fun setUp() {
        escenario = launch(MainActivity::class.java)
    }

    //se llama después del test
    @After
    fun tearDown() {
    }


    @Test
    fun test1() {
        onView(withId(R.id.chrReloj))
            .check(matches(withText("00:00")))
    }

    @Test
    fun test2() {
        onView(withId(R.id.btnInicio))
            .check(matches(isEnabled()))
        onView(withId(R.id.btnStop))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.btnPausa))
            .check(matches(isNotEnabled()))
        onView(withId(R.id.btnReiniciar))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun test3() {
        onView(withId(R.id.btnInicio))
            .perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.chrReloj))
            .check(matches(withText("00:05")))
    }

    @Test
    fun test4() {
        onView(withId(R.id.btnInicio))
            .perform(click())
        Thread.sleep(5000)
        escenario.recreate()
        onView(withId(R.id.chrReloj))
            .check(matches(withText("00:05")))
    }
}