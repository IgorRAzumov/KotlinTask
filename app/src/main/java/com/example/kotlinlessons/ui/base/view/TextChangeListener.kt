package com.example.kotlinlessons.ui.base.view

import android.text.TextWatcher

interface TextChangeListener : TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
}