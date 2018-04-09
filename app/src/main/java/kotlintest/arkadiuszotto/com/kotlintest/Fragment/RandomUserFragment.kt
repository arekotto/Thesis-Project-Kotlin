package kotlintest.arkadiuszotto.com.kotlintest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlintest.arkadiuszotto.com.kotlintest.Model.RandomUser

import kotlintest.arkadiuszotto.com.kotlintest.R
import kotlintest.arkadiuszotto.com.kotlintest.RandomUserAdapter
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class RandomUserFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>

    private val randomUsers = ArrayList<RandomUser>()

    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_random_user, container, false)
        recyclerView = inflate.findViewById(R.id.random_user_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        requestQueue = Volley.newRequestQueue(activity!!)
        adapter = RandomUserAdapter(randomUsers, activity)

        inflate.findViewById<Button>(R.id.new_user_button).setOnClickListener { fetchNewUser() }

        inflate.findViewById<Button>(R.id.clear_button).setOnClickListener {
            randomUsers.clear()
            adapter.notifyDataSetChanged()
        }

        recyclerView.adapter = adapter
        return inflate
    }

    private fun fetchNewUser() {
        val url = "https://randomuser.me/api/"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            Gson().fromJson(response, RandomUserResponse::class.java).let { parsedResponse ->
                parsedResponse.results?.first()?.let {
                    randomUsers.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }, { showNoConnectionToast() })
        requestQueue.add(stringRequest)
    }

    private inner class RandomUserResponse {
        @SerializedName("results")
        var results: List<RandomUser>? = null
    }

}