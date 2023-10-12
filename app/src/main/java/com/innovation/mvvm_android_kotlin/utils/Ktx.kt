package com.innovation.mvvm_android_kotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.innovation.mvvm_android_kotlin.R

/**
 *
 * Handle on click listener.
 *
 */
infix fun View.click(click: () -> Unit) {
    setOnClickListener { click() }
}

/**
 * This method show message on screen in from of toast.
 */
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

/**
 * Handle intent for activity.
 */
inline fun <reified T : Activity> Context.intentForActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}
//mContext.intentForActivity<ActivityName>()

/**
 * This method perform show and hide password.
 */
fun showHidePassword(textField: EditText, ivShowHide: ImageView) {
    if (textField.transformationMethod.equals(
            PasswordTransformationMethod.getInstance()
        )
    ) {
        ivShowHide.setImageResource(R.drawable.icon__eye_on)
        textField.transformationMethod = HideReturnsTransformationMethod.getInstance()
        textField.setSelection(textField.length())
    } else {
        ivShowHide.setImageResource(R.drawable.icon__eye_off)
        textField.transformationMethod = PasswordTransformationMethod.getInstance()
        textField.setSelection(textField.length())
    }
}