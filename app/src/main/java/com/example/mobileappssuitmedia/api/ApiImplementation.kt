package com.example.mobileappssuitmedia.api

import coil.network.HttpException
import com.example.mobileappssuitmedia.base.BaseUrl
import com.example.mobileappssuitmedia.data.dto.UserSource
import io.ktor.client.*
import io.ktor.client.request.*

class ApiImplementation(private val Http: HttpClient) : ApiService {
    override suspend fun getUserData(data:Int): UserSource {
        return try {
            Http.get {
                url(BaseUrl.BASE_LIST_USER)
                parameter("page",data)
            }
        } catch (e: io.ktor.utils.io.errors.IOException) {
            return UserSource(emptyList(), 0, 0, 0, 0, UserSource.Support("", ""))
        } catch (e: HttpException) {
            return UserSource(emptyList(), 0, 0, 0, 0, UserSource.Support("", ""))
        }catch (e:Exception){
            println("Error ${e.message}")
            return UserSource(emptyList(), 0, 0, 0, 0, UserSource.Support("", ""))
        }
    }
}