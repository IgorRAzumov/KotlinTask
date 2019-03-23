package com.example.kotlinlessons.remote

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.model.NoteResult
import com.github.ajalt.timberkt.Timber
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.Observable
import io.reactivex.Single

class FirestoreNotesSource : IRemoteNotesSource {
    companion object {
        private const val NOTES_COLLECTIONS = "NOTES_COLLECTIONS"
    }

    private val store by lazy {
        FirebaseFirestore.getInstance()
    }

    private val notesReference by lazy {
        store.collection(NOTES_COLLECTIONS)
    }

    override fun saveNote(note: Note): Single<NoteResult> {
        return Single.create { emitter ->
            notesReference
                .document(note.id)
                .set(note)
                .addOnSuccessListener { emitter.onSuccess(NoteResult.Success(note)) }
                .addOnFailureListener {
                    Timber.e(it)
                    emitter.onSuccess(NoteResult.Error(it))
                }
        }
    }

    override fun getNotes():  Observable<NoteResult> {
        return  Observable.create { emitter ->
            notesReference
                .addSnapshotListener { snapshot, e ->
                    e?.let {
                        Timber.e(e)
                        emitter.onNext(NoteResult.Error(e))
                    } ?: snapshot?.let {
                        val notes = mutableListOf<Note>()
                        var note: Note
                        for (doc: QueryDocumentSnapshot in snapshot) {
                            note = doc.toObject(Note::class.java)
                            notes.add(note)
                        }
                        emitter.onNext(NoteResult.Success(notes))
                    }
                }
        }
    }

    override fun getNoteById(id: String):Single<NoteResult> {
        return Single.create { emitter ->
            notesReference
                .document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    emitter.onSuccess(NoteResult.Success(snapshot.toObject(Note::class.java)))
                }.addOnFailureListener {
                    Timber.e(it)
                    emitter.onSuccess(NoteResult.Error(it))
                }
        }
    }

}