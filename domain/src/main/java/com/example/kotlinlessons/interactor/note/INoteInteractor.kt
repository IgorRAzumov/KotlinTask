package com.example.kotlinlessons.interactor.note

import com.example.kotlinlessons.model.ExequteResult
import io.reactivex.Single

interface INoteInteractor {
    fun getNoteById(noteId: String):Single<ExequteResult>
}