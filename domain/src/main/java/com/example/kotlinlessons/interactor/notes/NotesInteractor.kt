package com.example.kotlinlessons.interactor.notes

import com.example.kotlinlessons.repository.INotesRepository

class NotesInteractor(private val notesRepository: INotesRepository) : INotesInteractor {

    override fun getNotes() = notesRepository.getNotes()
}