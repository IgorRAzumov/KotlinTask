package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import io.reactivex.Observable
import io.reactivex.Single

interface INotesRepository {
    fun getNotes(): Observable<ExequteResult>

    fun saveNote(note: Note): Single<ExequteResult>

    fun getNoteById(id: String): Single<ExequteResult>
}