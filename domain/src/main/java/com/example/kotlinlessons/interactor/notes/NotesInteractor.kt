package com.example.kotlinlessons.interactor.notes

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.repository.INotesRepository
import io.reactivex.Single

class NotesInteractor(private var notesRepository: INotesRepository) : INotesInteractor {

    override fun getNotes(): Single<List<Note>> {
        return notesRepository.getNotes()
    }

}