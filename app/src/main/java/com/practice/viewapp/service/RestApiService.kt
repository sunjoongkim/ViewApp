package com.practice.viewapp.service

import com.practice.viewapp.data.Holiday
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiService {
    @GET("/api/v2/publicholidays/{year}/{locale}")
    fun getHolidays(
        @Path("year") year: String,
        @Path("locale") locale: String
    ): Call<List<Holiday>>
}