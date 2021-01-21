package com.jameshaisten.androidnasaexoplanetapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponseItem
import com.jameshaisten.androidnasaexoplanetapi.network.PlanetRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlanetViewModel : ViewModel() {


    val planetLiveData: MutableLiveData<List<PlanetResponseItem>> = MutableLiveData()
    private val planetRetrofit: PlanetRetrofit = PlanetRetrofit()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getSearchResults(searchQueryPlanet: String) {
        compositeDisposable.add(
            planetRetrofit.getSearchQuery("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    it
                }
                .subscribe({

                    planetLiveData.postValue(it)
                    Log.d("TAG_X", "what is it? it is: $it")
                    compositeDisposable.clear()
                },
                    {
                        Log.d("TAG_X", "Error...${it.localizedMessage}")
                    })
        )
    }


    override fun onCleared() {
        super.onCleared()
    }

}