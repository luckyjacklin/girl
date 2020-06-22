package me.jack.demo.girl.networking

import com.google.gson.Gson
import me.jack.demo.girl.data.api.GankService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Jack on 2020/6/20.
 */
object HttpClientProvider {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    fun retrofit(): Retrofit {
        val gson = Gson().newBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl(GankService.ENDPOINT)
            .callFactory(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}