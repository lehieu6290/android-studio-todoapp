package com.lth.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lth.todolist.room.Todo
import com.lth.todolist.room.TodoRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodoViewModel(val context: Context): ViewModel() {
    var todos: MutableLiveData<List<Todo>> = MutableLiveData()
    private val db = TodoRoomDatabase.getDatabase(context)
    private val todoDao = db.todoDao()
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    init {
        todos.value = arrayListOf()
        loadTodoList()
    }

    fun loadTodoList(){
        uiScope.launch {
            todos.value = todoDao.getAll()
        }
    }

    fun addTodo(todo: Todo){
        uiScope.launch {
            todoDao.insert(todo)
            loadTodoList()
        }
    }

    fun updateTodo(todo: Todo){
        uiScope.launch {
            todoDao.update(todo)
            loadTodoList()
        }
    }

    fun deleteTodo(todo: Todo){
        uiScope.launch {
            todoDao.delete(todo)
            loadTodoList()
        }
    }

    fun updateStatus(todo: Todo){
        uiScope.launch {
            todoDao.updateStatus(todo)
            loadTodoList()
        }
    }
}