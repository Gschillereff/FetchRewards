package com.neongarage.fetchrewards

import com.neongarage.fetchrewards.ui.main.Fetch
import com.neongarage.fetchrewards.ui.main.FetchAdapter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    class FetchAdapterTest{
        val fetchList: MutableList<Fetch> = mutableListOf()
        val fetchAdapter: FetchAdapter = FetchAdapter(fetchList)
        val fetch1: Fetch = Fetch(1, 1, "Item 1")
        val fetch2: Fetch = Fetch(3, 2, "Item 3")
        val fetch3: Fetch = Fetch(2, 3, "Item 2")
        val preSortedList: MutableList<Fetch> = mutableListOf(fetch1, fetch2, fetch3)


        @Test
        fun startsEmpty(){
            assertEquals(0, fetchAdapter.itemCount)
        }

        @Test
        fun sort(){
            fetchList.add(fetch3)
            fetchList.add(fetch1)
            fetchList.add(fetch2)

            //Inside filter function
            val noNullList: List<Fetch> = fetchList.filter { it.name != null }
            val noEmptyNameList: List<Fetch> = noNullList.filter { it.name != "" }
            //Filter using id for name, since name is item'id'
            val sortedList: List<Fetch> = noEmptyNameList.sortedWith(compareBy ({ it.listId }, {it.id}))
            fetchList.clear()
            fetchList.addAll(sortedList)

            assertEquals(preSortedList, fetchList)
        }
    }

}