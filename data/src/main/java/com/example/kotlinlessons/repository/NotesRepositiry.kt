package com.example.kotlinlessons.repository

import com.example.kotlinlessons.model.Note
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class NotesRepositiry : INotesRepository {
    private val notes = createNotes()

    override fun saveNote(note: Note): Completable = Completable.fromCallable {
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes[i] = note
                Completable.complete()
            }
        }
        notes.add(note)
        Completable.complete()
    }

    override fun getNotes(): Single<List<Note>> = Single.just(notes)

    private fun createNotes() = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Первая заметка",
            "Текст первой заметки. Не очень длинный, но интересный",
            color = Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Вторая заметка",
            "Текст второй заметки. Не очень длинный, но интересный",
            color = Note.Color.YELLOW
        ),
        Note(
            UUID.randomUUID().toString(),
            "Третья заметка",
            "Текст третьей заметки. Не очень длинный, но интересный",
            color = Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Четвертая заметка",
            "Текст четвертой заметки. Не очень длинный, но интересный",
            color = Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Пятая заметка",
            "Текст пятой заметки. Не очень длинный, но интересный",
            color = Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Шестая заметка",
            "Текст шестой заметки. Не очень длинный, но интересный",
            color = Note.Color.VIOLET
        )
    )
}