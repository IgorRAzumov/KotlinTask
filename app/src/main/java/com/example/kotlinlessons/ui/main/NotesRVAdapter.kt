package com.example.kotlinlessons.ui.main

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinlessons.R
import com.example.kotlinlessons.model.Note

class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NotesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )


    override fun getItemCount() = notes.size

    override fun onBindViewHolder(viewHolder: NotesRVAdapter.NotesViewHolder, position: Int) {
        viewHolder.bind(notes[position])
    }


    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.tv_title_note_item)!!
        private val contentText = itemView.findViewById<TextView>(R.id.tv_text_note_item)!!

        fun bind(note: Note) = with(note) {
            titleText.text = title
            contentText.text = text
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, parse1Color(color)))
            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }

        private fun parse1Color(color: Note.Color) = when (color) {
            Note.Color.WHITE -> R.color.white
            Note.Color.YELLOW -> R.color.yellow
            Note.Color.GREEN -> R.color.green
            Note.Color.BLUE -> R.color.blue
            Note.Color.RED -> R.color.red
            Note.Color.VIOLET -> R.color.violet
            Note.Color.PINK -> R.color.pink
        }
    }
}