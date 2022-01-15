package com.example.mobileappssuitmedia.local.repo

import com.example.mobileappssuitmedia.local.dao.DBDao
import com.example.mobileappssuitmedia.local.entity.LocalUserClass
import javax.inject.Inject

class Repositories @Inject constructor(
    private val dao: DBDao
) {
    suspend fun getAllData() = dao.getAllData()
    suspend fun insertData(data: LocalUserClass) = dao.insertData(data)
    suspend fun deleteAllData() = dao.deleteAll()
}