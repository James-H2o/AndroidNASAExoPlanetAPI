package com.jameshaisten.androidnasaexoplanetapi.network

import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.util.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PlanetRetrofit {

    private lateinit var planetAPI: PlanetAPI

    init {
        planetAPI = createPlanetAPI(createRetrofitInstance())
    }

    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun createPlanetAPI(retrofit: Retrofit): PlanetAPI = retrofit.create(PlanetAPI::class.java)

    fun getSearchQuery(searchQuery: String): Observable<PlanetResponse> = planetAPI.searchPlanet(searchQuery)

}

