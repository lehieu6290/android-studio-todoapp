package com.lth.todolist.model

class Todo(val title: String, val level: Int, val id: Int? = null, val status: Int? = null) {

    companion object{
        const val IMPORTANT: Int = 1
        const val NORMAL : Int = 2
        const val NOT_IMPORTANT: Int = 3

        const val DOING: Int = 0
        const val COMPLETE: Int = 1
    }
}