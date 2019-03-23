package com.example.kotlinlessons.interactor.notes

import com.example.kotlinlessons.model.NoteResult
import io.reactivex.Observable

interface INotesInteractor{
    fun getNotes():Observable<NoteResult>
}