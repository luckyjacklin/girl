package me.jack.demo.girl.data.model

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Jack on 2020/6/14.
 */
data class Data(
    @SerializedName("_id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("desc") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("category") val category: String,
    @SerializedName("type") val type: String,
    @SerializedName("createdAt") val createdDate: Date,
    @SerializedName("publishedAt") val publishedDate: Date,
    @SerializedName("images") val images: List<String>,
    @SerializedName("likeCounts") val likeCounts: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("views") val views: String
) {

    fun imageUrl() = url.replace("http://", "https://")

    fun formattedDate(): String =
        SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE)
            .format(publishedDate)

}