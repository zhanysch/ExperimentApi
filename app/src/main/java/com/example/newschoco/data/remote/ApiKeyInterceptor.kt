package com.example.newschoco.data.remote

import com.example.newschoco.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(requestBuilder)
    }
}
