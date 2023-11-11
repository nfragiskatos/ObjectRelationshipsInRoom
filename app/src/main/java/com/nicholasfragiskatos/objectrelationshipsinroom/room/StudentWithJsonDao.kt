package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface StudentWithJsonDao {
    @Insert
    fun saveStudentWithJson(student: StudentWithJsonEntity)
}
