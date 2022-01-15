package com.example.mobileappssuitmedia.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)