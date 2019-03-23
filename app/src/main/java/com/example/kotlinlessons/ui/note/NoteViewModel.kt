package com.example.kotlinlessons.ui.note

import com.example.kotlinlessons.interactor.add_note.AddNoteInteractor
import com.example.kotlinlessons.interactor.add_note.IAddNoteInteractor
import com.example.kotlinlessons.interactor.note.INoteInteractor
import com.example.kotlinlessons.interactor.note.NoteInteractor
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import com.example.kotlinlessons.remote.FirestoreNotesSource
import com.example.kotlinlessons.repository.NotesRepositiry
import com.example.kotlinlessons.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class NoteViewModel(private val addNoteInteractor: IAddNoteInteractor = AddNoteInteractor(NotesRepositiry(FirestoreNotesSource())),
                    private val noteInteractor: INoteInteractor = NoteInteractor(NotesRepositiry(FirestoreNotesSource()))) :
    BaseViewModel<Note?, NoteViewState>() {
    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            addNoteInteractor.addNote(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .addTo(disposables)
        }
    }

    fun loadNote(noteId: String) {
        noteInteractor.getNoteById(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { notesResult ->
                when (notesResult) {
                    is NoteResult.Success<*> -> {
                        viewStateLiveData.value = NoteViewState(notesResult.data as? Note)
                    }
                    is NoteResult.Error -> {
                        viewStateLiveData.value = NoteViewState(throwable = notesResult.error)
                    }
                }
            }
            .addTo(disposables)

    }
}