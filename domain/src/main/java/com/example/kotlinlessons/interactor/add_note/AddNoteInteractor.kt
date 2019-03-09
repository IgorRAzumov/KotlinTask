package com.example.kotlinlessons.interactor.add_note

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.repository.INotesRepository
import io.reactivex.Completable

class AddNoteInteractor(private var noteRepository: INotesRepository) : IAddNoteIntercator {

    override fun addNote(note: Note):Completable {
        return noteRepository.saveNote(note)
    }

}