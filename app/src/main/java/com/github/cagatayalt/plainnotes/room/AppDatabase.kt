package com.github.cagatayalt.plainnotes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotesTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNotesDao() : NotesDao

    companion object {
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context : Context) : AppDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,"app_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}