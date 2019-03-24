package com.example.kotlinlessons.ui.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.kotlinlessons.R
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseActivity
import com.example.kotlinlessons.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {
    override val layoutRes: Int = R.layout.activity_main

    lateinit var adapter: NotesRVAdapter

    override val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(tb_notes_toolbar)

        initRecycler()

        fab_notes.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    private fun initRecycler() {
        rv_notes.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter { NoteActivity.start(this, it.id) }

        rv_notes.adapter = adapter
    }

    override fun renderData(data: List<Note>?) {
        data?.let { adapter.notes = it }
    }
}
