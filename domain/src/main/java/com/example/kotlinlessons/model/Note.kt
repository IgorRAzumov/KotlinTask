package com.example.kotlinlessons.model

import java.io.Serializable
import java.util.*


data class Note(
    val id: String = "",
    val title: String = "",
    val text: String = "",
    val color: Note.Color = Color.WHITE,
    val lastChanged: Date = Date()
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Note) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    enum class Color {
        WHITE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
        PINK
    }
}