package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notesapp.Constant
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityUpdataNotesBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class UpdataNotesActivity : AppCompatActivity() , View.OnClickListener {
    lateinit var binding:ActivityUpdataNotesBinding
    lateinit var viewModel : NotesViewModel
    var priority ="1"
    lateinit var noText:TextView
    lateinit var yesText:TextView

    lateinit var receiveTitle:String;
    lateinit var receiveSubTitle:String;
    lateinit var receiveNotes:String;
    var receiveId:Int= 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdataNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(NotesViewModel::class.java)
        setUpReceivedData()
        setUpViewsWithReceivedDate()
        initListener()


    }

    private fun setUpViewsWithReceivedDate() {
        binding.updateTitleEditText.setText(receiveTitle)
        binding.updateSubTitleEditText.setText(receiveSubTitle)
        binding.updateNoteEditText.setText(receiveNotes)

    }

    private fun setUpReceivedData() {
        receiveTitle= intent.getStringExtra(Constant.KEY_TITLE)!!
        receiveSubTitle= intent.getStringExtra(Constant.KEY_SUBTITLE)!!
        receiveNotes= intent.getStringExtra(Constant.KEY_NOTES)!!
        receiveId= intent.getIntExtra(Constant.KEY_ID,0)
    }

    private fun initListener() {
        binding.updateGreenImage.setOnClickListener(this)
        binding.updateRedImage.setOnClickListener(this)
        binding.updateYellowImage.setOnClickListener(this)
        binding.updateNotesBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.update_green_image->{
                setImageResource(R.drawable.ic_baseline_done_24,0,0)
                priority="1"
            }
            R.id.update_yellow_image->{
                setImageResource(0,0,R.drawable.ic_baseline_done_24)
                priority="2"
            }
            R.id.update_red_image->{
                setImageResource(0,R.drawable.ic_baseline_done_24,0)
                priority="3"
            }
            R.id.updateNotesBtn->{
                val title= binding.updateTitleEditText.text.toString()
                val subTitle= binding.updateSubTitleEditText.text.toString()
                val notes= binding.updateNoteEditText.text.toString()
                updateNotes(title,subTitle,notes)
            }
        }

    }

    private fun updateNotes(title: String, subTitle: String, notes: String) {
        val sdfDate = SimpleDateFormat(Constant.DATE_FORMAT, Locale.ENGLISH) //dd/MM/yyyy
        val date = Date()
        val timeCurrent= sdfDate.format(date)

        val notes=Notes(id = receiveId, notesTitle = title, notesSubTitle = subTitle,
            notes = notes, notesPriority = priority, notesDate = timeCurrent)
        viewModel.updateNote(note = notes)
        Toast.makeText(this, "Note Updated Successfully . . ", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun setImageResource(i: Int, i1: Int, i2: Int) {
        binding.updateGreenImage.setImageResource(i)
        binding.updateRedImage.setImageResource(i1)
        binding.updateYellowImage.setImageResource(i2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ic_delete->{
                val bottomSheet= BottomSheetDialog(this, R.style.BottomSheetStyle)
                val view: View =LayoutInflater
                    .from(this).inflate(R.layout.delete_bottom_sheet,findViewById(R.id.bottom_sheet ))
                bottomSheet.setContentView(view)
                noText= view.findViewById(R.id.btn_no_delete)
                yesText= view.findViewById(R.id.btn_yes_delete)
                noText.setOnClickListener {
                    bottomSheet.dismiss()
                }
                yesText.setOnClickListener {
                    viewModel.deleteNote(receiveId)
                    Toast.makeText(this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                bottomSheet.show()

                //val tilte= view.findViewById(R.id)

            }
        }
        return true
    }
}