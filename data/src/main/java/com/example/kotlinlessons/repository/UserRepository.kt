package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.remote.auth.IAuthSource
import com.firebase.ui.auth.data.model.User
import com.github.ajalt.timberkt.Timber
import io.reactivex.Single

class UserRepository(private val authService: IAuthSource) : IUserRepository {

    private var user: User? = null

    override fun getUser(): Single<ExequteResult> {
        user?.let {
            return Single.just(ExequteResult.Success(user))
        } ?: return authService
            .getCurrentUser()
            .doOnSuccess { result ->
                if (result is ExequteResult.Success<*>) {
                    user = result.data as? User
                }
            }
            .doOnError { Timber.e(it) }
    }
}