package com.lth.todolist.view

import android.app.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.lth.todolist.R
import com.lth.todolist.room.Todo
import com.lth.todolist.viewmodel.TodoViewModel
import com.lth.todolist.viewmodel.TodoViewModelFactory

class FormDialog(private val todo: Todo? = null): DialogFragment() {
    private lateinit var formView: View
    private var level: Int = -1
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
        formView = LayoutInflater.from(context).inflate(R.layout.form, null)
        initForm()
        dialogBuilder.setView(formView)

        todoViewModel = ViewModelProviders.of(requireActivity(), TodoViewModelFactory(requireContext())).get(TodoViewModel::class.java)

        return  dialogBuilder.create()
    }

    private fun initForm(){
        val formTitle = formView.findViewById<TextView>(R.id.textViewFormTitle)
        val inputTitle = formView.findViewById<AppCompatEditText>(R.id.inputTitle)
        val radioButtonImportant = formView.findViewById<RadioButton>(R.id.radioButtonImportant)
        val radioButtonNormal = formView.findViewById<RadioButton>(R.id.radioButtonNormal)
        val radioButtonNotImportant = formView.findViewById<RadioButton>(R.id.radioButtonNotImportant)
        val buttonSubmitForm = formView.findViewById<Button>(R.id.buttonSubmitForm)

        if (todo != null){
            formTitle.text = "Cập nhật công việc"
            inputTitle.setText(todo.title)

            when(todo.level){
                Todo.IMPORTANT -> {
                    level = Todo.IMPORTANT
                    radioButtonImportant.isChecked = true
                }
                Todo.NORMAL -> {
                    level = Todo.NORMAL
                    radioButtonNormal.isChecked = true
                }
                Todo.NOT_IMPORTANT -> {
                    level = Todo.NOT_IMPORTANT
                    radioButtonNotImportant.isChecked = true
                }
            }

            buttonSubmitForm.text = "Cập nhật"
        }

        buttonSubmitForm.setOnClickListener {
            handleSubmitButtonClick()
        }

        formView.findViewById<Button>(R.id.buttonCloseForm).setOnClickListener {
            dismiss()
        }

        radioButtonImportant.setOnClickListener {
            level = Todo.IMPORTANT
        }

        radioButtonNormal.setOnClickListener {
            level = Todo.NORMAL
        }

        radioButtonNotImportant.setOnClickListener {
            level = Todo.NOT_IMPORTANT
        }
    }

    private fun checkForm(): Boolean{
        val title = formView.findViewById<AppCompatEditText>(R.id.inputTitle).text.toString()
        if(title == "" || level == -1){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun handleSubmitButtonClick(){
        if(!checkForm()) return

        val title = formView.findViewById<AppCompatEditText>(R.id.inputTitle).text.toString()

        if(todo == null){
            val newTodo = Todo(title = title, level = level)
            todoViewModel.addTodo(newTodo)
        }else{
            val newTodo = Todo(id = todo.id, title = title, level = level)
            todoViewModel.updateTodo(newTodo)
        }

        dismiss()
    }

}