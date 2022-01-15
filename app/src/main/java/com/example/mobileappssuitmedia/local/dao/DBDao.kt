package com.example.mobileappssuitmedia.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileappssuitmedia.local.entity.LocalUserClass

@Dao
interface DBDao {
    @Insert
    suspend fun insertData(data: LocalUserClass)

    @Query("DELETE FROM LocalUserClass")
    suspend fun deleteAll()

    @Query("SELECT * FROM LocalUserClass")
    suspend fun getAllData(): List<LocalUserClass>
}