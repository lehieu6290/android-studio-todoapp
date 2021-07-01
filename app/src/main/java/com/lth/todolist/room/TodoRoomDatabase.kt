package com.lth.todolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoRoomDatabase: RoomDatabase(){
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoRoomDatabase? = null

        fun getDatabase(context: Context): TodoRoomDatabase{
            if(instance != null){
                return instance as TodoRoomDatabase
            }else{
                instance = Room.databaseBuilder(context.applicationContext, TodoRoomDatabase::class.java, "todo_db").build()
                return instance as TodoRoomDatabase
            }
        }
    }
}