package com.lth.todolist.controller

import android.content.Context
import com.lth.todolist.model.Todo
import com.lth.todolist.utils.Database
import com.lth.todolist.view.MainActivity

class TodoController(val context: Context) {
    private val db = Database(context)

    fun addTodo(todo: Todo){
        db.insert(todo)
        reloadTodoList()
    }

    fun updateTodo(id: Int, todo: Todo){
        db.update(id, todo)
        reloadTodoList()
    }

    fun updateStatus(id: Int, status: Int){
        db.updateStatus(id, status)
        reloadTodoList()
    }

    fun deleteTodo(id: Int){
        db.delete(id)
        reloadTodoList()
    }

    fun reloadTodoList(){
        val todos = getTodoList()
        (context as MainActivity).reloadTodoList(todos)
    }

    fun getTodoList(): ArrayList<Todo>{
        var todos = ArrayList<Todo>()

        val cursor = db.selectAll()
        while (cursor.moveToNext()){
            val todo = Todo(cursor.getString(1), cursor.getInt(2), cursor.getInt(0), cursor.getInt(3))
            todos.add(todo)
        }

        return todos
    }
}