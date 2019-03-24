package com.example.kotlinlessons.interactor.add_note

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import io.reactivex.Single

interface IAddNoteInteractor {
    fun addNote(note: Note): Single<ExequteResult>
}