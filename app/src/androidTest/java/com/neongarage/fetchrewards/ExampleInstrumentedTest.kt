package com.neongarage.fetchrewards

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.neongarage.fetchrewards.ui.main.Fetch
import com.neongarage.fetchrewards.ui.main.FetchAdapter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val fetchList: MutableList<Fetch> = mutableListOf()
    val fetchAdapter: FetchAdapter = FetchAdapter(fetchList)
    val fetch1: Fetch = Fetch(1, 1, "Item 1")

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.neongarage.fetchrewards", appContext.packageName)
    }

    @Test
    fun adds(){
        fetchAdapter.addItem(fetch1)
        assertEquals(1, fetchAdapter.itemCount)

    }
}