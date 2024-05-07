package com.uiel.scul.feature.myPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uiel.scul.network.Retrofit

class MyPageViewModel : ViewModel() {
    val usersApi = Retrofit.usersApi


}
