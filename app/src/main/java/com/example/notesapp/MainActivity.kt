package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.ui.InsertNotesActivity
import com.example.notesapp.viewModel.NotesViewModel

class MainActivity : AppCompatActivity() ,View.OnClickListener{

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NotesAdapter
    lateinit var filteredNotesList:List<Notes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()

        viewModel= ViewModelProvider(this).get(NotesViewModel::class.java)

        loadData(0)

        binding.insertNotesBtn.setOnClickListener {v->
            val intent=Intent(this,InsertNotesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setAdapter(notes:List<Notes>){
        binding.notesRv.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter= NotesAdapter(notes,this)
        binding.notesRv.adapter=adapter
    }

    private fun initListener() {
        binding.noFilter.setOnClickListener(this)
        binding.lowToHigh.setOnClickListener(this)
        binding.highToLow.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when(view!!.id){
            R.id.no_filter->{
                loadData(0)
                setUpBackgroundViews(R.drawable.filter_selected_bg,R.drawable.filter_un_bg,R.drawable.filter_un_bg)
            }
            R.id.low_to_high->{
                loadData(1)
                setUpBackgroundViews(R.drawable.filter_un_bg,R.drawable.filter_un_bg,R.drawable.filter_selected_bg)
            }
            R.id.high_to_low->{
                loadData(2)
                setUpBackgroundViews(R.drawable.filter_un_bg,R.drawable.filter_selected_bg,R.drawable.filter_un_bg)
            }
        }
    }

    private fun loadData(i: Int) {
            when(i){
                0->{
                    viewModel.getAllNotes.observe(this) {notes->
                       setAdapter(notes)
                        filteredNotesList=notes
                    }
                }
                1->{
                    viewModel.lowToHigh.observe(this) {notes->
                        setAdapter(notes)
                        filteredNotesList=notes
                    }
                }
                2->{
                    viewModel.highToLow.observe(this) {notes->
                        setAdapter(notes)
                        filteredNotesList=notes
                    }
                }
            }
    }

    private fun setUpBackgroundViews(i0: Int, i1: Int, i2: Int) {
        binding.noFilter.setBackgroundResource(i0)
        binding.highToLow.setBackgroundResource(i1)
        binding.lowToHigh.setBackgroundResource(i2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val menuItem: MenuItem? =menu?.findItem(R.id.ic_search)
        val searchView:SearchView= menuItem?.actionView as SearchView
        searchView.queryHint="Search Notes Here . . "
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltered(newText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun notesFiltered(query: String?) {
        var filteredList: ArrayList<Notes> = ArrayList()

        filteredNotesList.forEach {note->
            if (note.notesTitle!!.contains(query!!)){
                filteredList.add(note)
            }
        }
        adapter.searchNotes(filteredList)
    }

}









