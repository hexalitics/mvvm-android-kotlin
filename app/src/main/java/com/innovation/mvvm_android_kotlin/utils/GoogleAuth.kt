package com.innovation.mvvm_android_kotlin.utils

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.innovation.mvvm_android_kotlin.datamodel.UserData
import com.innovation.mvvm_android_kotlin.ui.view.auth.LoginActivity
import com.innovation.mvvm_android_kotlin.ui.view.dashboard.HomeActivity
import com.innovation.mvvm_android_kotlin.ui.viewmodel.UserDataSingleton

open class GoogleAuth {

    class Builder(private val activity: Activity) {
        private var mGoogleSignInClient: GoogleSignInClient
        /**
         * Initialize Google SignIn Options.
         */
        init {
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build()
            mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
        }

        /**
         * Here perform google signIn
         *
         * @param resultLauncher
         */
        fun signIn(resultLauncher: ActivityResultLauncher<Intent>) {
            val signInIntent = mGoogleSignInClient.signInIntent
            resultLauncher.launch(signInIntent)
        }

        /**
         * Check user already google signIn.
         *
         * @return
         */
        fun isUserLogin(): Builder {
            val account = GoogleSignIn.getLastSignedInAccount(activity)
            if (account != null) {
                UserDataSingleton.userData.postValue(
                    UserData(
                        account.email.toString(),
                        account.photoUrl.toString(),
                        account.displayName.toString(),
                        SocialAuth.GOOGLE
                    )
                )
                gotoHomeScreen()
            }
            return this
        }

        /**
         * Here intent for home activity.
         *
         */
        private fun gotoHomeScreen() {
            activity.startActivity(
                Intent(
                    activity,
                    HomeActivity::class.java
                )
            )
            activity.finish()
        }

        /**
         * Here perform signOut from Google.
         *
         */
        fun signOutGoogle() {
            mGoogleSignInClient.signOut().addOnCompleteListener(
                activity
            ) {
                activity.startActivity(Intent(activity, LoginActivity::class.java))
                activity.finish()
            }
        }

        /**
         * Here Handle sign in result.
         *
         * @param result
         */
        fun handleSignInResult(result: ActivityResult) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            task.addOnCompleteListener {
                if (task.isSuccessful) {
                    try {
                        val account = task.getResult(ApiException::class.java)
                        if (account != null) {
                            UserDataSingleton.userData.postValue(
                                UserData(
                                    account.email.toString(),
                                    account.photoUrl.toString(),
                                    account.displayName.toString(),
                                    SocialAuth.GOOGLE
                                )
                            )
                            gotoHomeScreen()
                        }
                    } catch (e: ApiException) {
                        Log.e("ApiException","${e.status.statusMessage}")
                    }
                }
            }.addOnFailureListener {
                Log.e("FailureException","${it.message}")
            }
        }
    }
}