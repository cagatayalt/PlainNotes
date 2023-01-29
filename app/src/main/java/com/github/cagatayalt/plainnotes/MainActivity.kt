package com.github.cagatayalt.plainnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cagatayalt.plainnotes.databinding.ActivityMainBinding
import com.github.cagatayalt.plainnotes.room.AppDatabase
import com.github.cagatayalt.plainnotes.room.NotesDao

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var notesDao: NotesDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}