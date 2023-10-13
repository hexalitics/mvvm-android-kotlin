package com.innovation.mvvm_android_kotlin.ui.view.bindingadapter

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.innovation.mvvm_android_kotlin.R

object DataBindingAdapter {
    @BindingAdapter("app:onFocusChangeListener")
    @JvmStatic
    fun setOnFocusChangeListener(
        editText: TextInputEditText,
        onFocusChangeListener: View.OnFocusChangeListener?
    ) {
        editText.onFocusChangeListener = onFocusChangeListener
    }
}