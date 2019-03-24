package com.example.kotlinlessons.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.view.MenuItem
import com.example.kotlinlessons.R
import com.example.kotlinlessons.model.Note
import com.example.kotlinlessons.ui.base.BaseActivity
import com.example.kotlinlessons.ui.base.view.TextChangeListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {
    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "EXTRA_NOTE"
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val SAVE_DELAY = 500L

        fun start(context: Context, noteId: String? = null) {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            context.startActivity(intent)
        }
    }

    override val layoutRes = R.layout.activity_note

    override val model: NoteViewModel by viewModel()

    private val textChangeListener = object : TextChangeListener {
        private var timer = Timer()

        override fun afterTextChanged(s: Editable?) {
            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    saveNote()
                }
            }, SAVE_DELAY)
        }
    }

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(tb_act_note)
        supportActionBar?.setHomeButtonEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)

        noteId?.let {
            model.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.note_new_note)
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = if (this.note != null) {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(note!!.lastChanged)
        } else {
            getString(R.string.note_new_note)
        }

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        note?.let {
            et_title.setText(it.title)
            et_body.setText(it.text)
            tb_notes_toolbar.setBackgroundColor(ContextCompat.getColor(this, parseColor(it.color)))
        }
    }

    private fun parseColor(color: Note.Color) = when (color) {
        Note.Color.WHITE -> R.color.white
        Note.Color.YELLOW -> R.color.yellow
        Note.Color.GREEN -> R.color.green
        Note.Color.BLUE -> R.color.blue
        Note.Color.RED -> R.color.red
        Note.Color.VIOLET -> R.color.violet
        Note.Color.PINK -> R.color.pink
    }


    private fun saveNote() {
        if (et_title.text.isNullOrBlank() || et_title.text!!.length < 3) {
            return
        }

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date()
        ) ?: Note(
            UUID.randomUUID().toString(),
            et_title.text.toString(),
            et_body.text.toString()
        )

        note?.let { model.save(note!!) }
    }
}

