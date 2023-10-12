package com.innovation.mvvm_android_kotlin.datamodel

import com.innovation.mvvm_android_kotlin.utils.SocialAuth


data class UserData(val emailAddress : String , val profile : String , val name : String,val socialAuth : SocialAuth)