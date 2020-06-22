package me.jack.demo.girl.ui.base

/**
 * Created by Jack on 2020/6/14.
 */
interface BasePresenter<T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}