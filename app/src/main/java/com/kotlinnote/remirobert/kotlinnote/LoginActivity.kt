package com.kotlinnote.remirobert.kotlinnote

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    companion object {
        private val TAG = "LoginActivity"

        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val googleAccountProvider: GoogleAccountProvider = GoogleAccountProvider(this)

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d(TAG, firebaseAuth.currentUser.toString())

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        signInButton.setOnClickListener { view ->
            startSigningActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            try {
                val account = googleAccountProvider.handleResultWithIntentData(data)
                firebaseAuthWithGoogle(account)
                Log.d(TAG, account.displayName)
            } catch (e: ApiException) {
                Log.d(TAG, e.message)
            }
        }
    }

    private fun startSigningActivity() {
        val signInIntent = googleAccountProvider.intentSigIn()
        startActivityForResult(signInIntent, 1)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.getCurrentUser()
                        Log.d(TAG, "logged in success firebase")
                    } else {
                        Log.d(TAG, "failed login firebase")
                    }
                })
    }
}
