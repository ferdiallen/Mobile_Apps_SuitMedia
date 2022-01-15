package com.example.mobileappssuitmedia.presentation.third

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.mobileappssuitmedia.api.ApiService
import com.example.mobileappssuitmedia.local.entity.LocalUserClass
import com.example.mobileappssuitmedia.local.repo.Repositories
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModels @Inject constructor(
    private val clients: ApiService,
    private val db: Repositories
) : ViewModel() {
    init {
        viewModelScope.launch {
            if (db.getAllData().isNotEmpty()) db.deleteAllData()
        }
        loadData()
    }

    private var currPage = 1

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    val userData = mutableStateOf<List<LocalUserClass>>(listOf())

    fun loadNextPage() {
        currPage += 1
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        if (db.getAllData().isNotEmpty()) db.deleteAllData()
        delay(10L)
        _isRefresh.value = true
        val res = try {
            clients.getUserData(currPage)
        } catch (e: IOException) {
            return@launch
        } catch (e: HttpException) {
            return@launch
        }
        for (i in res.sourceData.indices) {
            db.insertData(
                LocalUserClass(
                    first_name = res.sourceData[i].first_name,
                    last_name = res.sourceData[i].last_name,
                    email = res.sourceData[i].email,
                    avatar = res.sourceData[i].avatar
                )
            )
        }
        userData.value = db.getAllData()
        _isRefresh.value = false
    }
}