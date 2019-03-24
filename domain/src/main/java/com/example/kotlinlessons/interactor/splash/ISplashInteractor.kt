package com.example.kotlinlessons.interactor.splash

import com.example.kotlinlessons.model.ExequteResult
import io.reactivex.Single

interface ISplashInteractor {
    fun getCurrentUser(): Single<ExequteResult>
}