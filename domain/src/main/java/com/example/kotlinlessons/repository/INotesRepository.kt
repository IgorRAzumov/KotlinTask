package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.Note
import io.reactivex.Completable
import io.reactivex.Single

interface INotesRepository {

    fun getNotes(): Single<List<Note>>

    fun saveNote(note: Note): Completable

}