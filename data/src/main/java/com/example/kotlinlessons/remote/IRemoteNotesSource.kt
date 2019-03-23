package com.example.kotlinlessons.remote

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import io.reactivex.Observable
import io.reactivex.Single

interface IRemoteNotesSource {
    fun getNoteById(id: String): Single<NoteResult>

    fun saveNote(note: Note): Single<NoteResult>

    fun getNotes(): Observable<NoteResult>
}