package com.innovation.mvvm_android_kotlin.ui.view.auth

import android.content.Context
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.innovation.mvvm_android_kotlin.R
import com.innovation.mvvm_android_kotlin.databinding.ActivityLoginBinding
import com.innovation.mvvm_android_kotlin.ui.viewmodel.LoginViewModel
import com.innovation.mvvm_android_kotlin.utils.GoogleAuth
import com.innovation.mvvm_android_kotlin.utils.click
import com.innovation.mvvm_android_kotlin.utils.showHidePassword
import com.innovation.mvvm_android_kotlin.utils.showToast

class LoginActivity : BaseActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var mContext: Context
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var googleAuth: GoogleAuth.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeSetup()
    }

    /**
     * Initialize variable and listeners.
     *
     */
    private fun initializeSetup() {
        mContext = this
        binding.loginViewModel = loginViewModel
        googleAuth = GoogleAuth.Builder(this@LoginActivity).isUserLogin()
        setObservers()
        binding.ivGoogle::click {
            googleAuth.signIn(googleAuthActivityResultLauncher)
        }
    }

    /**
     * Google auth activity result listener
     */
    private val googleAuthActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            googleAuth.handleSignInResult(result)
        }

    /**
     * Observe live data changes
     *
     */
    private fun setObservers() {
        loginViewModel.textFieldValidation.observe(this@LoginActivity) {
            mContext.showToast(it)
        }
        loginViewModel.isEmailAddressInvalid.observe(this@LoginActivity) {
            it?.let {
                if (it) {
                    binding.etEmailAddress.setBackgroundResource(R.drawable.bg_error_edit_text)
                } else {
                    binding.etEmailAddress.setBackgroundResource(R.drawable.bg_edit_text)
                }
            }
        }
        loginViewModel.isLoginSuccessful.observe(this@LoginActivity) {
            it?.let {
                if (it) {
                    mContext.showToast("Login Successfully.")

                }
            }
        }
        loginViewModel.hideShowPassword.observe(this@LoginActivity) {
            it?.let {
                showHidePassword(
                    binding.etPassword,
                    binding.ivShowHidePassword
                )
            }
        }
    }

}