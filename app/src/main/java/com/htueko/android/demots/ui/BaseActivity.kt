package com.htueko.android.demots.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.htueko.android.demots.repository.MainRepository
import com.htueko.android.demots.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    // instance of job, job is kind of background work in coroutine
    private lateinit var job: Job

    // gonna work on job + Main thread
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    // init the job instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainRepository.initialize(context = this)
        MovieRepository.initialize(context = this)
        job = Job()
    }

    // destroy the job
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}