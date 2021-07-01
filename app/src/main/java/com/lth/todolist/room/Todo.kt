package com.lth.todolist.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "status") val status: Int = 0,
){
    companion object{
        const val IMPORTANT: Int = 1
        const val NORMAL : Int = 2
        const val NOT_IMPORTANT: Int = 3

        const val DOING: Int = 0
        const val COMPLETE: Int = 1
    }
}
