package com.example.kotlinlessons.ui.note

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseViewState

class NoteViewState(note: Note?=null, throwable: Throwable? = null) : BaseViewState<Note?>(note, throwable)