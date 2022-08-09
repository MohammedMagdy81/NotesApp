package com.example.notesapp.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.entity.Notes
import com.example.notesapp.repo.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application){

    var repository:NotesRepository
    var getAllNotes : LiveData<List<Notes>>

    var highToLow : LiveData<List<Notes>>
    var lowToHigh : LiveData<List<Notes>>


    init {
        repository= NotesRepository(application.applicationContext)
        getAllNotes= repository.getAllNotes
        highToLow=repository.highToLow
        lowToHigh=repository.lowToHigh
    }
    fun insertNote(note:Notes)=repository.insertNotes(note)
    fun updateNote(note:Notes)=repository.updateNotes(note)
    fun deleteNote(id :Int)=repository.deleteNotes(id)
}