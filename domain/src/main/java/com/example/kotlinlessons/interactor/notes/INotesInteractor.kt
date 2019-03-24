package com.example.kotlinlessons.interactor.notes

import com.example.kotlinlessons.model.ExequteResult
import io.reactivex.Observable

interface INotesInteractor{
    fun getNotes():Observable<ExequteResult>
}