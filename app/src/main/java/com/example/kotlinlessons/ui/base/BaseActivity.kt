package com.example.kotlinlessons.ui.base

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kotlinlessons.R
import com.example.kotlinlessons.error.NoAuthException
import com.firebase.ui.auth.AuthUI

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 111
    }

    abstract val model: BaseViewModel<T, S>
    abstract val layoutRes: Int?

    abstract fun renderData(data: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let { setContentView(it) }

        model.getViewState().observe(this, Observer<S> { viewState ->
            viewState?.apply {
                data?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }

    private fun renderError(error: Throwable?) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error?.let { t ->
                t.message?.let {
                    showError(it)
                }
            }
        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun startLoginActivity() {
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.android_splash_logo)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }
}