package com.example.mobileappssuitmedia.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUserClass(
    @PrimaryKey val id: Int? = null,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)