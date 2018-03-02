package com.kotlinnote.remirobert.kotlinnote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.R.attr.data
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*


class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "MainActivity"
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("924694726774-1inguvfme05ma5cvgn72962ec4ecr9pa.apps.googleusercontent.com")
                .build()

        GoogleSignIn.getClient(this, gso)
    }

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        signInButton.setOnClickListener { view ->
            firebaseAuth.signOut()
            startSigninActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            handleSignResult(data)
        }
    }

    private fun startSigninActivity() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1)
    }

    private fun handleSignResult(data: Intent?) {
        val completedTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        if (completedTask.isComplete) {
            extractGoogleAccountFromTask(completedTask)
        }
    }

    private fun extractGoogleAccountFromTask(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
            Log.d(TAG, account.displayName)
        } catch (e: ApiException) {
            Log.d(TAG, e.message)
        }
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
