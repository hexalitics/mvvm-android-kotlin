package com.innovation.mvvm_android_kotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.innovation.mvvm_android_kotlin.datamodel.UserData

object UserDataSingleton {
    val userData : MutableLiveData<UserData?> = MutableLiveData()
}