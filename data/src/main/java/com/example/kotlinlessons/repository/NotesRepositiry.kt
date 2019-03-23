package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import com.example.kotlinlessons.remote.IRemoteNotesSource
import com.github.ajalt.timberkt.Timber
import io.reactivex.Observable
import io.reactivex.Single

class NotesRepositiry(private val remoteDataSource: IRemoteNotesSource) : INotesRepository {
    override fun getNoteById(id: String): Single<NoteResult> {
        return remoteDataSource
            .getNoteById(id)
            .doOnError { Timber.e(it) }
            .onErrorReturn { NoteResult.Error(it) }
    }

    override fun saveNote(note: Note): Single<NoteResult> {
        return remoteDataSource
            .saveNote(note)
            .doOnError { Timber.e(it) }
            .onErrorReturn { NoteResult.Error(it) }
    }

    override fun getNotes(): Observable<NoteResult> {
        return remoteDataSource
            .getNotes()
            .doOnError { Timber.e(it) }
            .onErrorReturn { NoteResult.Error(it) }
    }
}