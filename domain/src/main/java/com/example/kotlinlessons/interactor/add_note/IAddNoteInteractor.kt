package com.example.kotlinlessons.interactor.add_note

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import io.reactivex.Single

interface IAddNoteInteractor {
    fun addNote(note: Note): Single<NoteResult>
}