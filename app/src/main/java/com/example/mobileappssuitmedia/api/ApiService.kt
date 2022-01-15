package com.example.mobileappssuitmedia.api

import com.example.mobileappssuitmedia.data.dto.UserSource

interface ApiService {
    suspend fun getUserData(data:Int): UserSource
}