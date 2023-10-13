package com.innovation.mvvm_android_kotlin.ui.view.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.setContentView
import com.innovation.mvvm_android_kotlin.R
import com.innovation.mvvm_android_kotlin.databinding.ActivityHomeBinding
import com.innovation.mvvm_android_kotlin.databinding.ActivityLoginBinding
import com.innovation.mvvm_android_kotlin.ui.view.auth.BaseActivity
import com.innovation.mvvm_android_kotlin.ui.view.auth.LoginActivity
import com.innovation.mvvm_android_kotlin.ui.viewmodel.LoginViewModel
import com.innovation.mvvm_android_kotlin.ui.viewmodel.UserDataSingleton
import com.innovation.mvvm_android_kotlin.utils.GoogleAuth
import com.innovation.mvvm_android_kotlin.utils.SocialAuth
import com.innovation.mvvm_android_kotlin.utils.click
import com.innovation.mvvm_android_kotlin.utils.intentForActivity

class HomeActivity : BaseActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var mContext: Context
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var googleAuth: GoogleAuth.Builder
    private var socialAuth: SocialAuth? = null


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
        googleAuth = GoogleAuth.Builder(this@HomeActivity)
        setObservers()
    }


    /**
     * Observe live data changes
     *
     */
    private fun setObservers() {
        UserDataSingleton.userData.observe(this@HomeActivity) {
            it?.let {
                socialAuth = it.socialAuth
                when (it.socialAuth) {
                    SocialAuth.GOOGLE -> {
                        binding.tvUserName.text = it.name
                        binding.tvEmail.text = it.emailAddress
                    }
                }
            }
        }
        loginViewModel.userLogout.observe(this@HomeActivity){
            it?.let {
                if (it){
                    AlertDialog.Builder(this@HomeActivity)
                        .setTitle("Logout")
                        .setPositiveButton("Yes") { dialog, which ->
                            if (socialAuth == SocialAuth.GOOGLE){
                                googleAuth.signOutGoogle()
                            } else {
                                mContext.intentForActivity<HomeActivity> {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }
                                finish()
                            }
                        }
                        .setNegativeButton("No", null)
                        .setMessage("Are you sure want to logout?")
                        .show()
                }
            }
        }
    }

}