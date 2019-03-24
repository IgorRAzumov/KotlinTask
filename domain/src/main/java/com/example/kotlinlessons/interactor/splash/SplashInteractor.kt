package com.example.kotlinlessons.interactor.splash

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.repository.IUserRepository
import io.reactivex.Single

class SplashInteractor(private val userRepository: IUserRepository) : ISplashInteractor {

    override fun getCurrentUser(): Single<ExequteResult> {
        return userRepository.getUser()
    }

}