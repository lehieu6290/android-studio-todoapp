package com.lth.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lth.todolist.model.Todo
import com.lth.todolist.utils.Database

class TodoViewModel(val context: Context): ViewModel() {
    var todos: MutableLiveData<ArrayList<Todo>> = MutableLiveData()
    private val db = Database(context)

    init {
        loadTodoList()
    }

    fun loadTodoList(){
        todos.value = db.selectAll()
    }

    fun addTodo(todo: Todo){
        db.insert(todo)
        loadTodoList()
    }

    fun updateTodo(id: Int, todo: Todo){
        db.update(id, todo)
        loadTodoList()
    }

    fun deleteTodo(id: Int){
        db.delete(id)
        loadTodoList()
    }

    fun updateStatus(id: Int, status: Int){
        db.updateStatus(id, status)
        loadTodoList()
    }
}