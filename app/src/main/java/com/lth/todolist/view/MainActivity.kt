package com.lth.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lth.todolist.R

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayTodoList()

        setOnAddButtonClick()
    }

    private fun displayTodoList(){
        recyclerViewAdapter = RecyclerViewAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTodos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun setOnAddButtonClick(){
        findViewById<FloatingActionButton>(R.id.buttonAdd).setOnClickListener {
            val formDialog = FormDialog()
            formDialog.show(supportFragmentManager, null)
        }
    }
}