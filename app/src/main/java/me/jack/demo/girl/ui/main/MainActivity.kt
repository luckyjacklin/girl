package me.jack.demo.girl.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_data.view.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import me.jack.demo.girl.R
import me.jack.demo.girl.data.api.GankService
import me.jack.demo.girl.data.model.Data
import me.jack.demo.girl.data.source.DataRepository
import me.jack.demo.girl.extensions.gone
import me.jack.demo.girl.extensions.visible
import me.jack.demo.girl.networking.HttpClientProvider
import me.jack.demo.girl.ui.detail.DetailActivity
import me.jack.demo.girl.ui.view.InfiniteScrollListener
import me.jack.demo.girl.ui.view.RecyclerViewDivider

class MainActivity : AppCompatActivity(), MainContract.View {

    private val dataAdapter by lazy { DataAdapter() }
    private val presenter by lazy {
        MainPresenter(
            DataRepository(
                HttpClientProvider.retrofit().create(GankService::class.java)
            )
        )
    }
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        linearLayoutManager = LinearLayoutManager(this)
        with(list) {
            layoutManager = linearLayoutManager
            adapter = dataAdapter.apply {
                setOnItemClickListener(object : DataAdapter.OnItemClickListener {
                    override fun onItemClick(data: Data) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.DATA_TITLE, data.title)
                            putExtra(DetailActivity.DATA_DATE, data.formattedDate())
                            putExtra(DetailActivity.DATA_DESCRIPTION, data.description)
                            putExtra(DetailActivity.DATA_IMAGE_URL, data.imageUrl())
                        }
                        startActivity(intent)
                    }
                })
            }
            addItemDecoration(
                RecyclerViewDivider(
                    this@MainActivity,
                    R.dimen.divider,
                    android.R.color.transparent
                )
            )
            addOnScrollListener(object : InfiniteScrollListener(linearLayoutManager) {
                override fun onLoadMore() {
                    presenter.fetch()
                }

                override fun isDataLoading(): Boolean {
                    return presenter.isLoading()
                }

            })
        }
        presenter.attachView(this)
        presenter.fetch()
    }

    override fun showData(dataList: List<Data>) {
        list.visible()
        error.gone()
        dataAdapter.loadData(dataList)
    }

    override fun showNoData() {
        //TODO
    }

    override fun showError() {
        if (dataAdapter.itemCount == 0) {
            error.visible()
        }
    }

    override fun showLoading(active: Boolean) {
        if (active) {
            contentProgressBar.visible()
        } else {
            contentProgressBar.gone()
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

        private val dataList = emptyList<Data>().toMutableList()
        private var listener: OnItemClickListener? = null

        fun loadData(list: List<Data>) {
            dataList.addAll(list)
            notifyDataSetChanged()
        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(dataList[position])
        }

        override fun getItemCount(): Int = dataList.size

        inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

            fun bindData(data: Data) {
                view.itemTitle.text = data.title
                view.itemDescription.text = data.description
                view.itemDate.text = data.formattedDate()
                view.viewCounts.text = data.views
                view.likeCounts.text = data.likeCounts
                Glide.with(view).load(data.imageUrl()).into(view.itemImage)
                view.setOnClickListener {
                    listener?.onItemClick(data)
                }
            }

        }

        interface OnItemClickListener {
            fun onItemClick(data: Data)
        }
    }


}