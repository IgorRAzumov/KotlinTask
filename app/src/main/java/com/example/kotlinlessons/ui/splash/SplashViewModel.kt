package com.example.kotlinlessons.ui.splash

import com.example.kotlinlessons.error.NoAuthException
import com.example.kotlinlessons.ext.addTo
import com.example.kotlinlessons.interactor.splash.ISplashInteractor
import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val splashInteractor: ISplashInteractor) : BaseViewModel<Boolean?, SplashViewState>() {

    fun registerUser() {
        splashInteractor
            .getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    when (it) {
                        is ExequteResult.Success<*> -> {

                        }
                        is ExequteResult.Error -> {
                        }
                    }
                },
                onError = {
                    SplashViewState(error = NoAuthException())
                })
            .addTo(disposables)
    }
}