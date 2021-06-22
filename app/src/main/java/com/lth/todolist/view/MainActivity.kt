package com.lth.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lth.todolist.R
import com.lth.todolist.controller.TodoController
import com.lth.todolist.model.Todo
import java.io.Console
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var todos: ArrayList<Todo> = arrayListOf()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadTodoList()
        setOnAddButtonClick()
    }

    private fun loadTodoList(){
        val todoController = TodoController(this)
        todos = todoController.getTodoList()
        recyclerViewAdapter = RecyclerViewAdapter(todos, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTodos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    fun reloadTodoList(newTodos: ArrayList<Todo>){
        this.todos.clear()
        this.todos.addAll(newTodos)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun setOnAddButtonClick(){
        findViewById<FloatingActionButton>(R.id.buttonAdd).setOnClickListener {
            val formDialog = FormDialog()
            formDialog.show(supportFragmentManager, null)
        }
    }
}