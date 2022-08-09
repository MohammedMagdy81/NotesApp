package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Constant
import com.example.notesapp.entity.Notes

@Database(entities = arrayOf(Notes::class), version = Constant.DATABASE_VERSION, exportSchema = false)
abstract class NotesDatabase :RoomDatabase(){

    abstract fun notesDao(): NotesDao

    companion object{
        private var instance: NotesDatabase? = null

         fun getInstance(context:Context): NotesDatabase {
            if (instance ==null){
             instance = Room.databaseBuilder(context.applicationContext,
                 NotesDatabase::class.java,
                 Constant.DATABASE_NAME)
                 .allowMainThreadQueries()
                 .build()
            }
            return instance!!
        }
    }
}