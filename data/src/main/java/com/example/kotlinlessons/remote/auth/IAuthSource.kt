package com.example.kotlinlessons.remote.auth

import com.example.kotlinlessons.model.ExequteResult
import io.reactivex.Single

interface IAuthSource {
    fun getCurrentUser(): Single<ExequteResult>
}