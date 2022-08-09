package com.example.notesapp.ui

import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notesapp.Constant
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityInsertNotesBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

class InsertNotesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notesViewModel: NotesViewModel
    lateinit var binding : ActivityInsertNotesBinding
    var priority ="1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesViewModel= ViewModelProvider(this).get(NotesViewModel::class.java)
        initListener()
    }

    private fun initListener() {
        binding.greenImage.setOnClickListener(this)
        binding.redImage.setOnClickListener(this)
        binding.yellowImage.setOnClickListener(this)
        binding.doneNotesBtn.setOnClickListener(this)
    }

    private fun createNote(title: String, subTitle: String, notes: String) {
        val sdfDate = SimpleDateFormat(Constant.DATE_FORMAT,Locale.ENGLISH) //dd/MM/yyyy
        val date = Date()
        val timeCurrent= sdfDate.format(date)

        var note = Notes(notesTitle = title, notesSubTitle = subTitle,
            notes = notes, notesDate = timeCurrent, notesPriority = priority)
        notesViewModel.insertNote(note)
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.greenImage->{
                setImageResource(R.drawable.ic_baseline_done_24,0,0)
                priority="1"
            }
            R.id.yellowImage->{
                setImageResource(0,0,R.drawable.ic_baseline_done_24)
                priority="2"
            }
            R.id.redImage->{
                setImageResource(0,R.drawable.ic_baseline_done_24,0)
                priority="3"
            }
            R.id.doneNotesBtn->{
                val title = binding.insertTitleEditText.text.toString()
                val subTitle = binding.insertSubTitleEditText.text.toString()
                val notes = binding.insertNoteEditText.text.toString()
                createNote(title,subTitle,notes)
            }
        }
    }

    private fun setImageResource(i: Int, i1: Int, i2: Int) {
        binding.greenImage.setImageResource(i)
        binding.redImage.setImageResource(i1)
        binding.yellowImage.setImageResource(i2)
    }
}




