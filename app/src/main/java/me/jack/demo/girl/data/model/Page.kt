package me.jack.demo.girl.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jack on 2020/6/14.
 */
data class Page(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("page") val index: Int,
    @SerializedName("page_count") val count: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("total_counts") val totalCounts: Int
)