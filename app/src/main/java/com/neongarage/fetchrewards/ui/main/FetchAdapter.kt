package com.neongarage.fetchrewards.ui.main

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neongarage.fetchrewards.R

class FetchAdapter (private val fetchList: MutableList<Fetch>):
    RecyclerView.Adapter<FetchAdapter.ViewHolder>(){
    private val backUpList: MutableList<Fetch> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val idText: TextView = itemView.findViewById(R.id.id_TV)
            val listIdText: TextView = itemView.findViewById(R.id.listId_TV)
            val nameText: TextView = itemView.findViewById(R.id.name_TV)

        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fetch_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            idText.text = fetchList[position].id.toString()
            listIdText.text = fetchList[position].listId.toString()
            nameText.text = fetchList[position].name
        }
    }

    fun addItem(fetch: Fetch){
        fetchList.add(fetch)
        backUpList.add(fetch)
    }

    fun filterNulls(){
        val noNullList: List<Fetch> = fetchList.filter { it.name != null }
        val noEmptyNameList: List<Fetch> = noNullList.filter { it.name != "" }
        //Filter using id for name, since name is item'id'
        val sortedList: List<Fetch> = noEmptyNameList.sortedWith(compareBy ({ it.listId }, {it.id}))
        fetchList.clear()
        fetchList.addAll(sortedList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = fetchList.size
}