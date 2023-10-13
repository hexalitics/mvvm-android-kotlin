package com.innovation.mvvm_android_kotlin.ui.viewmodel

import android.util.Patterns
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innovation.mvvm_android_kotlin.R

class LoginViewModel : ViewModel() {

    val textFieldValidation = MutableLiveData<String>()
    val isLoginSuccessful =  MutableLiveData<Boolean>()
    val userLogout =  MutableLiveData<Boolean>()
    val hideShowPassword =  MutableLiveData<Boolean>()
    val emailAddress = ObservableField<String>()
    val password = ObservableField<String>()
    val isEmailAddressInvalid = MutableLiveData<Boolean>()
    val focus = true

    /**
     * This method Perform login after validate data.
     *
     */
    fun performLogin() {
        if (isValidateLoginData()) {
            isLoginSuccessful.postValue(true)
        }
    }

    /**
     * This method Perform logout.
     *
     */
    fun performLogout() {
        userLogout.postValue(true)
    }

    fun hideAndShowPassword() {
        hideShowPassword.postValue(true)
    }

    /**
     * This method validate login data.
     *
     */
    private fun isValidateLoginData(): Boolean {
        return if (emailAddress.get().isNullOrEmpty()) {
            textFieldValidation.postValue("Please enter email address")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress.get().toString()).matches()) {
            textFieldValidation.postValue("Please enter valid email address")
            isEmailAddressInvalid.postValue(true)
            false
        } else if (password.get().isNullOrEmpty()) {
            isEmailAddressInvalid.postValue(false)
            textFieldValidation.postValue("Please enter password")
            false
        } else {
            isEmailAddressInvalid.postValue(false)
            true
        }
    }


    val onFocusChange = View.OnFocusChangeListener { view, hasFocus ->
        if (hasFocus) {
            view.setBackgroundResource(R.drawable.bg_focus_edit_text)
        } else {
            view.setBackgroundResource(R.drawable.bg_edit_text)
        }
    }

}