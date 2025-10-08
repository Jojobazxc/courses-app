package com.example.data.network.interceptors

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockCoursesInterceptor(
    private val context: Context,
    private val jsonFileName: String = "courses.json"
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val json = loadFromAssets(context, jsonFileName)
        Log.d("Interceptor", "Requst Intercepted")
        return Response.Builder()
            .code(200)
            .protocol(okhttp3.Protocol.HTTP_1_1)
            .message("OK")
            .request(chain.request())
            .body(json.toResponseBody("application/json".toMediaType()))
            .build()
    }

    private fun loadFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

}