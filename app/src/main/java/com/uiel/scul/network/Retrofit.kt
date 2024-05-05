package com.uiel.scul.network

import com.uiel.scul.BuildConfig
import com.uiel.scul.network.api.CultureApi
import com.uiel.scul.network.api.ReviewApi
import com.uiel.scul.network.api.UsersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
object Retrofit {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val usersApi: UsersApi by lazy { retrofit.create(UsersApi::class.java) }
    val cultureApi: CultureApi by lazy { retrofit.create(CultureApi::class.java) }
    val reviewApi: ReviewApi by lazy { retrofit.create(ReviewApi::class.java) }
}
