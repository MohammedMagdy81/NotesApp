package com.example.notesapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Constant
import com.example.notesapp.R
import com.example.notesapp.entity.Notes
import com.example.notesapp.ui.UpdataNotesActivity

class NotesAdapter(var notes: List<Notes>,var context: Context):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

      var allNotesItem:List<Notes> = ArrayList<Notes>(notes)


    fun searchNotes(filteredNotes:List<Notes>){
        this.notes=filteredNotes
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position],context)
    }

    override fun getItemCount()= notes.size

    class NotesViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        val priority= itemView.findViewById<View>(R.id.item_notes_image)
        val tiltle = itemView.findViewById<TextView>(R.id.item_notes_title)
        val subTiltle = itemView.findViewById<TextView>(R.id.item_notes_sub_title)
        val date = itemView.findViewById<TextView>(R.id.item_notes_date)

        fun bind(notes: Notes,context: Context) {
            tiltle.text = notes.notesTitle
            subTiltle.text= notes.notesSubTitle
            date.text=notes.notesDate
            when(notes.notesPriority){
                "1"->{
                    priority.setBackgroundResource(R.drawable.green_shape)
                }
                "2"->{
                    priority.setBackgroundResource(R.drawable.yellow_shape)
                }
                "3"->{
                    priority.setBackgroundResource(R.drawable.red_shape)
                }
            }

            itemView.setOnClickListener {view->
                val intent= Intent(context,UpdataNotesActivity::class.java)
                intent.putExtra(Constant.KEY_TITLE,notes.notesTitle)
                intent.putExtra(Constant.KEY_SUBTITLE,notes.notesSubTitle)
                intent.putExtra(Constant.KEY_NOTES,notes.notes)
                intent.putExtra(Constant.KEY_ID,notes.id)
                context.startActivity(intent)
            }

        }

    }
}







