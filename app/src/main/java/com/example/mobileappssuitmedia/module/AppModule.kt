package com.example.mobileappssuitmedia.module

import android.content.Context
import androidx.room.Room
import com.example.mobileappssuitmedia.api.ApiImplementation
import com.example.mobileappssuitmedia.api.ApiService
import com.example.mobileappssuitmedia.local.dao.DBDao
import com.example.mobileappssuitmedia.local.databases.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun createKtorClient(): ApiService {
        return ApiImplementation(Http = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        })
    }

    @Singleton
    @Provides
    fun providesDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LocalDatabase::class.java, "user_db").build()

    @Provides
    @Singleton
    fun providesDao(db: LocalDatabase): DBDao = db.getDao()

}