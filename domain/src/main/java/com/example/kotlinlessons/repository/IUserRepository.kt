package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.ExequteResult
import io.reactivex.Single

interface IUserRepository {
    fun getUser(): Single<ExequteResult>
}