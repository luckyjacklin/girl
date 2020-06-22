package me.jack.demo.girl.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import me.jack.demo.girl.R

/**
 * Created by Jack on 2020/6/22.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        intent?.let {
            val title = it.getStringExtra(DATA_TITLE)
            supportActionBar?.title = title
            val date = it.getStringExtra(DATA_DATE)
            supportActionBar?.subtitle = date
            val description = it.getStringExtra(DATA_DESCRIPTION)
            detailDescription.text = description
            val imageUrl = it.getStringExtra(DATA_IMAGE_URL)
            Glide.with(this).load(imageUrl).into(detailImage)
        }
    }

    companion object {
        const val DATA_TITLE = "DATA_TITLE"
        const val DATA_DATE = "DATA_DATE"
        const val DATA_DESCRIPTION = "DATA_DESCRIPTION"
        const val DATA_IMAGE_URL = "DATA_IMAGE_URL"
    }

}