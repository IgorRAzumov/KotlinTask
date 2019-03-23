package com.example.kotlinlessons.ui.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()

    open val disposables = CompositeDisposable()

    open fun getViewState(): LiveData<S> = viewStateLiveData

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}