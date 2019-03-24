package com.example.kotlinlessons.model

sealed class ExequteResult {
    data class Success<out T>(val data: T) : ExequteResult()
    data class Error(val error: Throwable?) : ExequteResult()
}