package com.example.kotlinlessons.interactor.note

import com.example.kotlinlessons.repository.INotesRepository

class NoteInteractor(private val notesRepository: INotesRepository) : INoteInteractor {

    override fun getNoteById(noteId: String) = notesRepository.getNoteById(noteId)
}