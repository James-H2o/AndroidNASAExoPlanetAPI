package com.jameshaisten.androidnasaexoplanetapi.network

import com.jameshaisten.androidnasaexoplanetapi.model.PlanetResponse
import com.jameshaisten.androidnasaexoplanetapi.util.Constants.Companion.PLANET_PATH
import com.jameshaisten.androidnasaexoplanetapi.util.Constants.Companion.SEARCH_QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetAPI {

    @GET(PLANET_PATH)
    fun searchPlanet(@Query(SEARCH_QUERY) searchQueryPlanet: String): Observable<PlanetResponse>

}