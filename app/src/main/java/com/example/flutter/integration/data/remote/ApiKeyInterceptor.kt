package com.example.flutter.integration.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        // Add the API key as a query parameter
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .build()

        // Create a new request with the modified URL
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}