package com.htueko.android.demots.ui.upcoming

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.htueko.android.demots.R
import com.htueko.android.demots.adapter.MovieRecyclerViewAdapter
import com.htueko.android.demots.repository.MovieRepository
import kotlinx.android.synthetic.main.up_coming_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpComingFragment : Fragment() {


    private val nowRepo by lazy {
        MovieRepository.get()
    }

    companion object {
        fun newInstance() =
            UpComingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("upcoming", "onCreateView()")
        val view = inflater.inflate(R.layout.up_coming_fragment, container, false)
        setupUi()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("upcoming", "onActivityCreated()")
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupUi(){
        CoroutineScope(Dispatchers.IO)
            .launch {
                val altResult = nowRepo.getNowPlayingMovies()
                //val result = repository.getUpComingMovies()
                withContext(Dispatchers.Main){
                    //Log.d("upcoming", "$result")
                    //Toast.makeText(activity?.applicationContext, "UpComing: $result", Toast.LENGTH_LONG).show()
                    recycler_up_coming.apply {
                        layoutManager = LinearLayoutManager(activity?.applicationContext)
                        adapter = MovieRecyclerViewAdapter(altResult)
                    }
                }
            }
    }

}
