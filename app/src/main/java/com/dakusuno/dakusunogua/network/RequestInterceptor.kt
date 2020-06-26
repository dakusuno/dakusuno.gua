package com.dakusuno.dakusunogua.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RequestInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().header("Authorization","token 0a9403d617e036d222622644f0eb4adacd7338be").url(originalRequest.url).build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }

}