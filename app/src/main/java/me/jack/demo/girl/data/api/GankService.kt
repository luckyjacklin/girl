package me.jack.demo.girl.data.api

import me.jack.demo.girl.data.model.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Jack on 2020/6/14.
 */
interface GankService {

    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/10")
    fun fetchData(@Path("page") page: Int): Call<Page>

    companion object {
        const val ENDPOINT = "https://gank.io/"
    }
}