package com.uttarakhand.kisanseva2.network

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://buyfreshdtu.xyz/"

    fun getRetrofit(context: Context?): Retrofit? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("content-type", "application/json")
            Log.d("SavedToken", PreferenceManager.getDefaultSharedPreferences(context).getString("token", "")!!)
            requestBuilder.addHeader("token", FirebaseAuth.getInstance().currentUser!!.uid)

            val request = requestBuilder.build()
            chain.proceed(request)
        })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)

        val cookieJar: ClearableCookieJar =
                PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

        val client = httpClient.cookieJar(cookieJar).build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }
        return retrofit
    }
}