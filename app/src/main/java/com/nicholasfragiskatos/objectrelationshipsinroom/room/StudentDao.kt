package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<StudentEntity>
}