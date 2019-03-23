package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import io.reactivex.Observable
import io.reactivex.Single

interface INotesRepository {
    fun getNotes(): Observable<NoteResult>

    fun saveNote(note: Note): Single<NoteResult>

    fun getNoteById(id: String): Single<NoteResult>
}