package com.htueko.android.demots.ui.nowplaying

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.htueko.android.demots.R
import com.htueko.android.demots.adapter.MovieRecyclerViewAdapter
import com.htueko.android.demots.data.api.ApiRepository
import com.htueko.android.demots.data.model.MovieModel
import com.htueko.android.demots.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NowPlayingFragment : Fragment() {

    private val repository by lazy {
        MovieRepository.get()
    }

    companion object {
        fun newInstance() =
            NowPlayingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("nowplaying", "onCreateView()")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_now_playing, container, false)
        setupUi()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("nowplaying", "onActivityCreated()")
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupUi() {
        CoroutineScope(Dispatchers.IO)
            .launch {
                val list = ApiRepository.fetchNowPlayingMovies()
                Log.d("----IO----", "$list")
                //val result = repository.getNowPlayingMovies()
                withContext(Dispatchers.Main) {
                    Log.d("----Main----", "$list")
                    recycler_now_playing.apply {
                        layoutManager = LinearLayoutManager(activity?.applicationContext)
                        adapter = MovieRecyclerViewAdapter(list)
                    }
                }
            }
    }

}
