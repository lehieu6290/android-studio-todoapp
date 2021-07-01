package com.lth.todolist.room

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Update
    suspend fun updateStatus(todo: Todo)
}