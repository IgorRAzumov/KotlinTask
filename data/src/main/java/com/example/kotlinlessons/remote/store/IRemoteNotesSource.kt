package com.example.kotlinlessons.remote.store

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import io.reactivex.Observable
import io.reactivex.Single

interface IRemoteNotesSource {
    fun getNoteById(id: String): Single<ExequteResult>

    fun saveNote(note: Note): Single<ExequteResult>

    fun getNotes(): Observable<ExequteResult>
}