package com.jameshaisten.androidnasaexoplanetapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.network.PlanetRetrofit
import com.jameshaisten.androidnasaexoplanetapi.util.DebugLogger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val planetLiveData: MutableLiveData<PlanetResponse> = MutableLiveData()
    private val planetRetrofit: PlanetRetrofit = PlanetRetrofit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

planetLiveData.observe(this, Observer
{
    DebugLogger.log("Results obtained: ${it.size}")
})

    }


    fun getSearchResults(searchQueryPlanet: String){
        compositeDisposable.add(
                planetRetrofit.getSearchQuery("")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map {
                            it
                        }
                        .subscribe({
                            planetLiveData.postValue(it)
                            compositeDisposable.clear()
                        },
                                {

                                })
        )
    }

}