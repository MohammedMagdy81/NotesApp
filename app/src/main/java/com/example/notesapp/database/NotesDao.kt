package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.entity.Notes
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface NotesDao {


    @Query("SELECT * FROM Notes_Database ")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes_Database ORDER BY notesPriority DESC ")
    fun highToLow (): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes_Database ORDER BY notesPriority ASC ")
    fun lowToHigh(): LiveData<List<Notes>>

    @Insert
    fun insertNote(note : Notes)

    @Update(onConflict =OnConflictStrategy.REPLACE)
    fun updateNote(note: Notes)

    @Query("DELETE FROM Notes_Database WHERE id = :id")
    fun deleteNotes(id : Int)
}