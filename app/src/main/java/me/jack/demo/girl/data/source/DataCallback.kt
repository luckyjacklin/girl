package me.jack.demo.girl.data.source

/**
 * Created by Jack on 2020/6/14.
 */
interface DataCallback<in T : Any> {
    fun onDataLoaded(data: T)
    fun onError(t: Throwable)
}