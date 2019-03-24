package com.example.kotlinlessons.ui.splash

import android.os.Handler
import com.example.kotlinlessons.ui.base.BaseActivity
import com.example.kotlinlessons.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {
    companion object {
        private const val START_DELAY = 1000L
    }

    override val layoutRes: Int? = null

    override val model: SplashViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ model.registerUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}
