package com.example.kotlinlessons.ui.note

import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}