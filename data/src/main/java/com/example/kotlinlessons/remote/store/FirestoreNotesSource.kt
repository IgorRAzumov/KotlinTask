package com.example.kotlinlessons.remote.store

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.Note
import com.github.ajalt.timberkt.Timber
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.Observable
import io.reactivex.Single

class FirestoreNotesSource(private val store:FirebaseFirestore) :
    IRemoteNotesSource {
    companion object {
        private const val NOTES_COLLECTIONS = "NOTES_COLLECTIONS"
    }

    private val notesReference by lazy {
        store.collection(NOTES_COLLECTIONS)
    }

    override fun saveNote(note: Note): Single<ExequteResult> {
        return Single.create { emitter ->
            notesReference
                .document(note.id)
                .set(note)
                .addOnSuccessListener { emitter.onSuccess(ExequteResult.Success(note)) }
                .addOnFailureListener {
                    Timber.e(it)
                    emitter.onSuccess(ExequteResult.Error(it))
                }
        }
    }

    override fun getNotes():  Observable<ExequteResult> {
        return  Observable.create { emitter ->
            notesReference
                .addSnapshotListener { snapshot, e ->
                    e?.let {
                        Timber.e(e)
                        emitter.onNext(ExequteResult.Error(e))
                    } ?: snapshot?.let {
                        val notes = mutableListOf<Note>()
                        var note: Note
                        for (doc: QueryDocumentSnapshot in snapshot) {
                            note = doc.toObject(Note::class.java)
                            notes.add(note)
                        }
                        emitter.onNext(ExequteResult.Success(notes))
                    }
                }
        }
    }

    override fun getNoteById(id: String):Single<ExequteResult> {
        return Single.create { emitter ->
            notesReference
                .document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    emitter.onSuccess(ExequteResult.Success(snapshot.toObject(Note::class.java)))
                }.addOnFailureListener {
                    Timber.e(it)
                    emitter.onSuccess(ExequteResult.Error(it))
                }
        }
    }

}