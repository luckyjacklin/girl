package me.jack.demo.girl.ui.main

import me.jack.demo.girl.data.model.Data
import me.jack.demo.girl.ui.base.BasePresenter
import me.jack.demo.girl.ui.base.BaseView

/**
 * Created by Jack on 2020/6/14.
 */
interface MainContract {

    interface View : BaseView {
        fun showData(dataList: List<Data>)
        fun showNoData()
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun fetch()
        fun isLoading(): Boolean
    }
}