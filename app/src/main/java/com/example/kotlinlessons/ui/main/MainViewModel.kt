package com.example.kotlinlessons.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlinlessons.ext.addTo
import com.example.kotlinlessons.interactor.notes.INotesInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(notesInteractor: INotesInteractor) : ViewModel() {
    private lateinit var viewStateLiveData: MutableLiveData<MainViewState>
    private val disposables = CompositeDisposable()

    init {
        notesInteractor
            .getNotes()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe { notes ->
                viewStateLiveData.value = viewStateLiveData.value?.copy(notes = notes!!) ?: MainViewState(notes)
            }
            .addTo(disposables)
    }


    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}