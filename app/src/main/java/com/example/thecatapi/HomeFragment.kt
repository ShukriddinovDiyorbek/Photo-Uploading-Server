package com.example.thecatapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.thecatapi.adapter.SearchAdapter
import com.example.thecatapi.database.ApiClient
import com.example.thecatapi.model.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var root: View
    lateinit var recyclerView: RecyclerView
    lateinit var manager: StaggeredGridLayoutManager
    lateinit var adapter: SearchAdapter
    private var list =  ArrayList<ResponseItem>()
    lateinit var lottie_animation: LottieAnimationView
    var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        initViews()

        return root
    }

    private fun initViews() {
        recyclerView = root.findViewById(R.id.recyclerViewHome)
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = manager
        lottie_animation = root.findViewById(R.id.lottie_animation)
        refreshAdapter(list)
        apiGetPhotosRetrofit()
    }
    private fun apiGetPhotosRetrofit(){
        counter++
        lottie_animation.visibility = View.VISIBLE
        val apiClient = ApiClient(requireContext())
        apiClient.apiServise.getAll(20,counter).enqueue(object : Callback<ArrayList<ResponseItem>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<ResponseItem>>,
                response: Response<ArrayList<ResponseItem>>
            ) {
                lottie_animation.visibility = View.INVISIBLE
                list.addAll(response.body()!!)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<ResponseItem>>, t: Throwable) {

            }

        })

    }
    fun refreshAdapter(list: ArrayList<ResponseItem>){
        adapter = SearchAdapter(list)
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    apiGetPhotosRetrofit()
                }
            }
        })
        recyclerView.adapter = adapter
    }
}