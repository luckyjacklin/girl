package me.jack.demo.girl.data.source

import me.jack.demo.girl.data.api.GankService
import me.jack.demo.girl.data.model.Page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Created by Jack on 2020/6/14.
 */
class DataRepository(private val service: GankService) {

    fun fetch(page: Int, callback: DataCallback<Page>) {
        service.fetchData(page).enqueue(object : Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback.onDataLoaded(body)
                        return
                    }
                }
                callback.onError(IOException("fetch data error ${response.code()} ${response.message()}"))
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}