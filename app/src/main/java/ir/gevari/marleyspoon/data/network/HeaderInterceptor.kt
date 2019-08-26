package ir.gevari.marleyspoon.data.network

import ir.gevari.marleyspoon.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", BuildConfig.CONTENTFUL_DELIVERY_TOKEN)
            .build()
        return chain.proceed(request)
    }
}