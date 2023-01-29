package com.github.cagatayalt.plainnotes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Insert
    fun insertNote(notesTable: NotesTable)

    @Update
    fun updateNote(notesTable: NotesTable)

    @Query("SELECT * FROM NotesTable")
    fun getAllNotes():List<NotesTable>

    @Query("DELETE from NotesTable where id=:id")
    fun deleteNote(id:Int)
}