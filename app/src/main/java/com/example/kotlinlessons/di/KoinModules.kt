package com.example.kotlinlessons.di

import com.example.kotlinlessons.interactor.add_note.AddNoteInteractor
import com.example.kotlinlessons.interactor.add_note.IAddNoteInteractor
import com.example.kotlinlessons.interactor.note.INoteInteractor
import com.example.kotlinlessons.interactor.note.NoteInteractor
import com.example.kotlinlessons.interactor.notes.INotesInteractor
import com.example.kotlinlessons.interactor.notes.NotesInteractor
import com.example.kotlinlessons.interactor.splash.ISplashInteractor
import com.example.kotlinlessons.interactor.splash.SplashInteractor
import com.example.kotlinlessons.remote.store.FirestoreNotesSource
import com.example.kotlinlessons.remote.store.IRemoteNotesSource
import com.example.kotlinlessons.repository.INotesRepository
import com.example.kotlinlessons.repository.NotesRepositiry
import com.example.kotlinlessons.ui.main.MainViewModel
import com.example.kotlinlessons.ui.note.NoteViewModel
import com.example.kotlinlessons.ui.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }

    single<IRemoteNotesSource> { FirestoreNotesSource(get()) }
    single<INotesRepository> { NotesRepositiry(get()) }
}

val mainModule = module {
    factory<INotesInteractor> { NotesInteractor(get()) }
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    factory<IAddNoteInteractor> { AddNoteInteractor(get()) }
    factory<INoteInteractor> { NoteInteractor(get()) }
    viewModel { NoteViewModel(get(), get()) }
}

val splashModule = module {
    factory<ISplashInteractor> { SplashInteractor(get()) }
    viewModel { SplashViewModel(get()) }
}