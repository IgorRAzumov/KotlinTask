package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.remote.store.IRemoteNotesSource
import com.github.ajalt.timberkt.Timber
import io.reactivex.Observable
import io.reactivex.Single

class NotesRepositiry(private val remoteDataSource: IRemoteNotesSource) : INotesRepository {
    override fun getNoteById(id: String): Single<ExequteResult> {
        return remoteDataSource
            .getNoteById(id)
            .doOnError { Timber.e(it) }
            .onErrorReturn { ExequteResult.Error(it) }
    }

    override fun saveNote(note: Note): Single<ExequteResult> {
        return remoteDataSource
            .saveNote(note)
            .doOnError { Timber.e(it) }
            .onErrorReturn { ExequteResult.Error(it) }
    }

    override fun getNotes(): Observable<ExequteResult> {
        return remoteDataSource
            .getNotes()
            .doOnError { Timber.e(it) }
            .onErrorReturn { ExequteResult.Error(it) }
    }
}