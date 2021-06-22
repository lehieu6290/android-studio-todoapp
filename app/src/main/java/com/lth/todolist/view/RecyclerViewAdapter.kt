package com.lth.todolist.view

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lth.todolist.R
import com.lth.todolist.controller.TodoController
import com.lth.todolist.model.Todo

class RecyclerViewAdapter(private val todos: ArrayList<Todo>, val mainActivity: MainActivity): RecyclerView.Adapter<RecyclerViewAdapter.TodoItemHolder>() {

    inner class TodoItemHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewLevel: TextView = view.findViewById(R.id.textViewLevel)
        val todoBody: LinearLayout = view.findViewById(R.id.todoBody)
        val buttonDelete: ImageView = view.findViewById(R.id.buttonDelete)
        val checkBoxStatus: CheckBox = view.findViewById(R.id.checkBoxStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoItemHolder(view)
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        val todoController = TodoController(mainActivity)
        val todo = todos[position]
        holder.textViewTitle.text = todo.title

        holder.todoBody.setOnClickListener {
            val formDialog = FormDialog(todo)
            formDialog.show(mainActivity.supportFragmentManager, null)
        }

        when(todo.status){
            Todo.DOING -> {
                holder.checkBoxStatus.isChecked = false
                holder.textViewTitle.paintFlags = 0
            }
            Todo.COMPLETE -> {
                holder.checkBoxStatus.isChecked = true
                holder.textViewTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        when(todo.level) {
            Todo.IMPORTANT -> {
                holder.textViewLevel.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.important))
                holder.textViewLevel.text = "Quan trọng"
            }
            Todo.NORMAL -> {
                holder.textViewLevel.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.normal))
                holder.textViewLevel.text = "Bình thường"
            }
            Todo.NOT_IMPORTANT -> {
                holder.textViewLevel.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.not_important))
                holder.textViewLevel.text = "Không quan trọng"
            }
        }

        holder.buttonDelete.setOnClickListener {
            todo.id?.let { it -> todoController.deleteTodo(it) }
        }

        holder.checkBoxStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            val status = if(isChecked) Todo.COMPLETE else Todo.DOING

            todo.id?.let { todoController.updateStatus(it, status) }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}