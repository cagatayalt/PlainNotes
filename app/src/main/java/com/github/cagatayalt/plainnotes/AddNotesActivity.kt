package com.github.cagatayalt.plainnotes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.github.cagatayalt.plainnotes.databinding.ActivityAddNotesBinding
import com.github.cagatayalt.plainnotes.room.AppDatabase
import com.github.cagatayalt.plainnotes.room.NotesDao
import com.github.cagatayalt.plainnotes.room.NotesTable
import java.util.*


class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNotesBinding
    private lateinit var type: String
    private var noteId: Int = -1
    private lateinit var noteTitle:String
    private lateinit var noteDesc:String


    private lateinit var appDatabase: AppDatabase
    private lateinit var notesDao: NotesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.titleEt.apply {
            imeOptions = EditorInfo.IME_ACTION_DONE
            isSingleLine = true
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Close the keyboard
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(windowToken, 0)
                    // Perform other actions here, if needed
                    true
                } else {
                    false
                }
            }
        }

        binding.descEt.apply {
            imeOptions = EditorInfo.IME_ACTION_DONE
            isSingleLine = true // Somehow, if this line is deleted, the action button does not change even though all the code except this one
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Close the keyboard
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(windowToken, 0)
                    // Perform other actions here, if needed
                    true
                } else {
                    false
                }
            }
        }







        type = intent.getStringExtra("type").toString()
        if (type.equals("Update")) {
            supportActionBar?.setTitle("Update Note")
            noteId = intent.getIntExtra("id",-1)
            noteTitle = intent.getStringExtra("title").toString()
            noteDesc = intent.getStringExtra("desc").toString()

            binding.titleEt.setText(noteTitle)
            binding.descEt.setText(noteDesc)


        } else {
            supportActionBar?.setTitle("Add Note")
        }


        appDatabase = AppDatabase.getDatabase(this)
        notesDao = appDatabase.getNotesDao()

        binding.saveNote.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val desc = binding.descEt.text.toString()
            if (type.equals("Add")) {
                val model = NotesTable(0,title,desc, Calendar.getInstance().timeInMillis)
                notesDao.insertNote(model)
            } else {
                val model = NotesTable(noteId,title,desc, Calendar.getInstance().timeInMillis)
                notesDao.updateNote(model)

            }
            finish()
        }
    }


}