package com.example.data.network.api

import com.example.data.network.models.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoursesApi {

    @GET("/courses")
    suspend fun getCourses(): Response<CoursesResponse>

}