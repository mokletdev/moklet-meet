package com.amirlabs.moklet_meet.data.api.core

import android.annotation.SuppressLint
import com.amirlabs.moklet_meet.BuildConfig
import com.amirlabs.moklet_meet.data.api.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object NetworkConfig {
    fun getApi(): ApiService {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory


        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            logging.level = HttpLoggingInterceptor.Level.BODY
        else
            logging.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}