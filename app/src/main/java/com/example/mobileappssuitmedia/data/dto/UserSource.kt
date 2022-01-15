package com.example.mobileappssuitmedia.data.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSource(
    @SerialName("data") val sourceData: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val support: Support
) {
    @Serializable
    data class Support(
        val url: String, val text: String
    )
}