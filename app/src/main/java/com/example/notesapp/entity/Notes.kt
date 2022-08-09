package com.example.notesapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes_Database")
data class Notes(@PrimaryKey(autoGenerate = true)
                  var id : Int?=null,
                 @ColumnInfo
                  var notesTitle:String?=null,
                 @ColumnInfo
                  var notesSubTitle:String?=null,
                 @ColumnInfo
                  var notesDate:String?=null,
                 @ColumnInfo
                  var notes:String?=null,
                 @ColumnInfo
                  var notesPriority:String?=null)