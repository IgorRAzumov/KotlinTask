package com.example.kotlinlessons.interactor.add_note

import com.example.kotlinlessons.model.Note
import io.reactivex.Completable

interface IAddNoteIntercator {
    fun addNote(note: Note): Completable
}