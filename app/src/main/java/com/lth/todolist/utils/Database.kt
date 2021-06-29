package com.lth.todolist.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lth.todolist.model.Todo

class Database(context: Context) {
    private val db: SQLiteDatabase

    init {
        val helper = CustomSQLiteOpenHelper(context)
        db = helper.writableDatabase
    }

    companion object {
        const val TABLE_ROW_ID = "_id"
        const val TABLE_ROW_TITLE = "title"
        const val TABLE_ROW_LEVEL = "level"
        const val TABLE_ROW_STATUS = "status"
        private const val DB_NAME = "lth"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "todos"
    }

    fun insert(todo: Todo) {
        val query = "INSERT INTO " + TABLE_NAME + " (" +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_LEVEL +
                ") " +
                "VALUES (" +
                "'" + todo.title + "'" + ", " +
                todo.level +
                ");"

        db.execSQL(query)
    }

    fun update(id: Int, todo: Todo){
        val query = "UPDATE " + TABLE_NAME + " SET " +
                TABLE_ROW_TITLE + "='" + todo.title + "', " +
                TABLE_ROW_LEVEL + "=" + todo.level +
                " WHERE " + TABLE_ROW_ID + "=" + id

        db.execSQL(query)
    }

    fun updateStatus(id: Int, status: Int){
        val query = "UPDATE $TABLE_NAME SET $TABLE_ROW_STATUS=$status WHERE $TABLE_ROW_ID=$id"
        db.execSQL(query)
    }

    fun delete(id: Int){
        val query = "DELETE FROM $TABLE_NAME WHERE $TABLE_ROW_ID = $id"

        db.execSQL(query)
    }

    fun selectAll(): ArrayList<Todo> {
        val cursor = db.rawQuery("SELECT * from $TABLE_NAME", null)
        var todos = ArrayList<Todo>()

        while (cursor.moveToNext()){
            val todo = Todo(cursor.getString(1), cursor.getInt(2), cursor.getInt(0), cursor.getInt(3))
            todos.add(todo)
        }

        return todos
    }

    private inner class CustomSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val newTableQueryString = ("create table "
                    + TABLE_NAME + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_TITLE
                    + " text not null,"
                    + TABLE_ROW_LEVEL
                    + " integer not null,"
                    + TABLE_ROW_STATUS
                    + " integer default 0);")
            db.execSQL(newTableQueryString)

            db.close()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }
    }
}