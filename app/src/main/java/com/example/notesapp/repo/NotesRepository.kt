package com.example.notesapp.repo

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notesapp.database.NotesDao
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.entity.Notes

class NotesRepository(context: Context) {

    var notesDao: NotesDao
    var getAllNotes : LiveData<List<Notes>>
    var highToLow : LiveData<List<Notes>>
    var lowToHigh : LiveData<List<Notes>>


    init {

        var notesDatabase: NotesDatabase = NotesDatabase.getInstance(context)
        notesDao= notesDatabase.notesDao()
        getAllNotes=notesDao.getAllNotes()
        highToLow=notesDao.highToLow()
        lowToHigh=notesDao.lowToHigh()
    }

    fun getAllNotes(): LiveData<List<Notes>> =notesDao.getAllNotes()

    fun insertNotes(note:Notes){
        notesDao.insertNote(note)
    }

    fun updateNotes(note:Notes){
        notesDao.updateNote(note)
    }

    fun deleteNotes(id: Int){
        notesDao.deleteNotes(id)
    }


}