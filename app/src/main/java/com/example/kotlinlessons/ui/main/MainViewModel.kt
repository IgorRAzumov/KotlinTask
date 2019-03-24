package com.example.kotlinlessons.ui.main

import com.example.kotlinlessons.ext.addTo
import com.example.kotlinlessons.interactor.notes.INotesInteractor
import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val notesInteractor: INotesInteractor) :
    BaseViewModel<List<Note>?, MainViewState>() {

    init {
        viewStateLiveData.value = MainViewState()
        loadNotes()
    }

    private fun loadNotes() {
        notesInteractor.getNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    when (it) {
                        is ExequteResult.Success<*> -> {
                            viewStateLiveData.value = MainViewState(it.data as? List<Note>)
                        }
                        is ExequteResult.Error -> {
                            viewStateLiveData.value = MainViewState(error = it.error)
                        }
                    }
                },
                onError = {
                    viewStateLiveData.value = MainViewState(error = it)
                }
            ).addTo(disposables)
    }


}