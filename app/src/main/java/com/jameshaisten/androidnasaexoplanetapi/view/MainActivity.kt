package com.jameshaisten.androidnasaexoplanetapi.view

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.jameshaisten.androidnasaexoplanetapi.R
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.network.PlanetRetrofit
import com.jameshaisten.androidnasaexoplanetapi.util.DebugLogger
import com.jameshaisten.androidnasaexoplanetapi.view.adapter.PlanetAdapter
import com.jameshaisten.androidnasaexoplanetapi.viewmodel.PlanetViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


//Homework Week 4 Day 2
//Create an application that pulls from an api of your choice
//App should be:
//- MVVM
//- use LiveData
//- use RxJava
//- use Kotlin
//- use SnapHelper
//- RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: PlanetViewModel by viewModels()
    private lateinit var planetRecyclerView: RecyclerView
    private val planetAdapter: PlanetAdapter = PlanetAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        planetRecyclerView = findViewById(R.id.recyclerview_main)
        planetRecyclerView.adapter = planetAdapter

        viewModel.planetLiveData.observe(this, Observer {
            planetAdapter.updatePlanetList(it as PlanetResponse)
        })

        Log.d("TAG_X", "Starting...")
        viewModel.getSearchResults("")
    }
}