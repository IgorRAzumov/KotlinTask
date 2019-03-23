package com.example.kotlinlessons.interactor.add_note

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.repository.INotesRepository

class AddNoteInteractor(private val noteRepository: INotesRepository) : IAddNoteInteractor {

    override fun addNote(note: Note) = noteRepository.saveNote(note)
}