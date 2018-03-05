package com.kotlinnote.remirobert.kotlinnote

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

/**
 * Created by remirobert on 03/03/2018.
 */

class GoogleAccountProvider constructor(listenerActivity: Activity) {
    private val listenerActivity: Activity = listenerActivity

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("924694726774-1inguvfme05ma5cvgn72962ec4ecr9pa.apps.googleusercontent.com")
                .build()

        GoogleSignIn.getClient(listenerActivity, gso)
    }

    @Throws(ApiException::class)
    fun handleResultWithIntentData(data: Intent?): GoogleSignInAccount {
        val completedTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        return extractGoogleAccountFromTask(completedTask)
    }

    private fun extractGoogleAccountFromTask(completedTask: Task<GoogleSignInAccount>): GoogleSignInAccount {
        return completedTask.getResult(ApiException::class.java)
    }

    fun intentSigIn(): Intent {
        return googleSignInClient.signInIntent
    }
}