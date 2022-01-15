package com.example.mobileappssuitmedia.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _nameText = mutableStateOf("")
    val nameText: State<String> = _nameText

    private val _paliandromeText = mutableStateOf("")
    val paliandromeText: State<String> = _paliandromeText

    fun setNameText(data: String) {
        _nameText.value = data
    }

    fun setPaliandromeChange(data:String){
        _paliandromeText.value = data
    }
}