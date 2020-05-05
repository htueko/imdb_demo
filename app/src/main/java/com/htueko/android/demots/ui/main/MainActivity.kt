package com.htueko.android.demots.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.htueko.android.demots.R
import com.htueko.android.demots.adapter.MyViewPagerAdapter
import com.htueko.android.demots.data.api.ApiRepository
import com.htueko.android.demots.data.database.ProfileSharePref
import com.htueko.android.demots.data.model.MovieModel
import com.htueko.android.demots.repository.MainRepository
import com.htueko.android.demots.ui.BaseActivity
import com.htueko.android.demots.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private val repository by lazy {
        MainRepository.get()
    }
    private val sharePref by lazy {
        ProfileSharePref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.nav_view)

        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val id: Int = item.itemId
                when (id) {
                    R.id.action_profile -> {
                        startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                    }
                    R.id.action_now_playing -> {
                        Toast.makeText(this@MainActivity, "Now Playing", Toast.LENGTH_SHORT).show()
                    }
                    R.id.action_up_coming -> {
                        Toast.makeText(this@MainActivity, "Up Coming", Toast.LENGTH_SHORT).show()
                    }
                    else -> return true
                }
                return true
            }
        })

        view_pager.adapter =
            MyViewPagerAdapter(this)
        TabLayoutMediator(tabs_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text =
                    if (position == 0) resources.getString(R.string.text_now_playing) else resources.getString(
                        R.string.text_up_coming
                    )
            }).attach()

        launch {
            ApiRepository.getNowPlayingMovies(
                onSuccess = ::nowPlayingMoviesFetched,
                onError = ::onError
            )
            //ApiRepository.getUpComingMovies(onSuccess = ::upComingMoviesFetched,onError = ::onError)
        }

//        val navHeaderView = nav_view.inflateHeaderView(R.layout.nav_header)
//        val name = navigationView.findViewById<TextView>(R.id.tv_name_nav_header)
//        val email = navigationView.findViewById<TextView>(R.id.tv_email_nav_header)
//        val getPrefEmail = sharePref.getEmail(resources.getString(R.string.KEY_EMAIL))
//        val getPrefName = sharePref.getEmail(resources.getString(R.string.KEY_NAME))
//        if (getPrefEmail != null){
//            email.text = getPrefEmail.toString()
//        }else{
//            email.text = "adamdev@hotmail.comm"
//        }
//        if (getPrefName != null){
//            name.text = getPrefName.toString()
//        }else{
//            name.text = "Adam Dev"
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun nowPlayingMoviesFetched(movies: List<MovieModel>) {
        launch {
            withContext(Dispatchers.IO) {
                repository.insertAllMovies(movies)
                Log.d("TAG", "$movies.forEach { it.id }")
            }
        }
    }

    private fun upComingMoviesFetched(movies: List<MovieModel>) {
        launch {
            withContext(Dispatchers.IO) {
                repository.insertAllMovies(movies)
            }
        }
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_msg), Toast.LENGTH_SHORT).show()
    }

}
