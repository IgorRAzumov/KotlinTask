package com.example.kotlinlessons.ui.note

import com.example.kotlinlessons.interactor.add_note.IAddNoteInteractor
import com.example.kotlinlessons.interactor.note.INoteInteractor
import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NoteViewModel(private val addNoteInteractor: IAddNoteInteractor, private val noteInteractor: INoteInteractor) :
    BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private val currenNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun save(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    fun loadNote(noteId: String) {
        noteInteractor.getNoteById(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { notesResult ->
                viewStateLiveData.value = when (notesResult) {
                    is ExequteResult.Success<*> -> NoteViewState(NoteViewState.Data(note = notesResult.data as Note?))
                    is ExequteResult.Error -> NoteViewState(error = notesResult.error)
                }
            }, onError = {
                viewStateLiveData.value = NoteViewState(error = it)
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        currenNote?.let {
            addNoteInteractor.addNote(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .addTo(disposables)
        }
    }
}