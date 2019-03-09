package com.example.kotlinlessons.interactor.notes

import com.example.kotlinlessons.model.Note
import io.reactivex.Single

interface INotesInteractor{
    fun getNotes(): Single<List<Note>>
}