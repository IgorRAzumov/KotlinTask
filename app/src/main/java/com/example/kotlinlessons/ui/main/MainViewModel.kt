package com.example.kotlinlessons.ui.main

import com.example.kotlinlessons.ext.addTo
import com.example.kotlinlessons.interactor.notes.INotesInteractor
import com.example.kotlinlessons.interactor.notes.NotesInteractor
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import com.example.kotlinlessons.remote.FirestoreNotesSource
import com.example.kotlinlessons.repository.NotesRepositiry
import com.example.kotlinlessons.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val notesInteractor: INotesInteractor = NotesInteractor(NotesRepositiry(FirestoreNotesSource()))) :
    BaseViewModel<List<Note>?, MainViewState>() {

    init {
        viewStateLiveData.value = MainViewState()
        loadNotes()
    }

    private fun loadNotes() {
        notesInteractor
            .getNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    when (it) {
                        is NoteResult.Success<*> -> {
                            viewStateLiveData.value = MainViewState(it.data as? List<Note>)
                        }
                        is NoteResult.Error -> {
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