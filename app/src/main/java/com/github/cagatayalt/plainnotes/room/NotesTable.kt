package com.github.cagatayalt.plainnotes.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesTable(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val title: String,
    val description: String,
    val dateAdded: Long
)
