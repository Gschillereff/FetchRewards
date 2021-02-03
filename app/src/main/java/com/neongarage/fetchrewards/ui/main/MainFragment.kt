package com.neongarage.fetchrewards.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.neongarage.fetchrewards.R
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.neongarage.fetchrewards.databinding.MainFragmentBinding
import org.json.JSONArray

class MainFragment : Fragment() {

    val fetchList: MutableList<Fetch> = mutableListOf()
    var fetchAdapter: FetchAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        binding.fetchRv.adapter = FetchAdapter(fetchList)
        binding.fetchRv.layoutManager = LinearLayoutManager(context)
        fetchAdapter = binding.fetchRv.adapter as FetchAdapter

        call()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun call() {
        Log.d("test", "inside call")
        val queue = Volley.newRequestQueue(context)
        val url =
            "https://fetch-hiring.s3.amazonaws.com/hiring.json"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                // val jsonArray = JSONObject(response.toString()).getJSONArray("")
                val jsonArray = JSONArray(response.toString())
                response(jsonArray)
            },
            { })
        queue.add(stringRequest)
    }


    private fun response(response: JSONArray) {
        for (i in 0 until response.length()) {
            val jsonObject = response.getJSONObject(i)
            val gson = Gson()
            val fetchObject = gson.fromJson(jsonObject.toString(), Fetch::class.java)
            fetchAdapter?.addItem(fetchObject)
        }
        fetchAdapter?.filter()
    }
}