package com.example.mobileappssuitmedia.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileappssuitmedia.local.dao.DBDao
import com.example.mobileappssuitmedia.local.entity.LocalUserClass

@Database(entities = [LocalUserClass::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getDao():DBDao
}