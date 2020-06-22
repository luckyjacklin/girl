package me.jack.demo.girl.ui.main

import me.jack.demo.girl.data.model.Page
import me.jack.demo.girl.data.source.DataCallback
import me.jack.demo.girl.data.source.DataRepository

/**
 * Created by Jack on 2020/6/14.
 */
class MainPresenter(
    private val repository: DataRepository
) : MainContract.Presenter {

    private var view: MainContract.View? = null
    private var page: Int = 1
    private var isLoading: Boolean = false

    override fun fetch() {
        view?.showLoading(true)
        isLoading = true
        repository.fetch(page++, object : DataCallback<Page> {
            override fun onDataLoaded(data: Page) {
                isLoading = false
                view?.showLoading(false)
                if (data.data.isNotEmpty()) {
                    view?.showData(data.data)
                } else {
                    view?.showNoData()
                }
            }

            override fun onError(t: Throwable) {
                isLoading = false
                view?.showLoading(false)
                view?.showError()
            }
        })
    }

    override fun isLoading(): Boolean {
        return isLoading
    }

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}