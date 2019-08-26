package ir.gevari.marleyspoon.data.network

import com.contentful.java.cda.CDAClient
import com.safframework.http.interceptor.LoggingInterceptor
import ir.gevari.marleyspoon.BuildConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiClient {

    operator fun invoke(): CDAClient {

        val connectionTimeOut: Long = 10
        val readTimeOut: Long = 10

        val cdaClient = CDAClient.builder()
            .setSpace(BuildConfig.CONTENTFUL_SPACE_ID)

        val loggingInterceptor = LoggingInterceptor.Builder()
            .loggable(true)
            .request()
            .requestTag("Request")
            .response()
            .responseTag("Response")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(readTimeOut, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        return cdaClient.setCallFactory(okHttpClient).build()
    }

}