package com.github.cagatayalt.plainnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cagatayalt.plainnotes.databinding.ActivityMainBinding
import com.github.cagatayalt.plainnotes.room.AppDatabase
import com.github.cagatayalt.plainnotes.room.NotesDao

class MainActivity : MyInterface, AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var notesDao: NotesDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.title = "My Notes"

        appDatabase = AppDatabase.getDatabase(this)
        notesDao = appDatabase.getNotesDao()

        binding.floatingBtn.setOnClickListener {
            startActivity(Intent(this,AddNotesActivity::class.java).putExtra("type","Add"))
        }


        setContentView(view)
    }


    private fun loadAllNotes() {
        val items = notesDao.getAllNotes().reversed()
        binding.recyclerView.adapter = RvAdapter(this,items,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadAllNotes()
    }

    override fun onClick(id: Int) {
        notesDao.deleteNote(id)
        loadAllNotes()
    }


}