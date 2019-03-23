package com.example.kotlinlessons.ui.main

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)