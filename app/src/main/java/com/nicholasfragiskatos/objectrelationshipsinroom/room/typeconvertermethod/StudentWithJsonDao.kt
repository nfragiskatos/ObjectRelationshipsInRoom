package com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentWithJsonDao {
    @Insert
    fun saveStudentWithJson(student: StudentWithJsonEntity)

    @Query("SELECT * FROM studentWithJson")
    fun getAllStudentsWithJson(): List<StudentWithJsonEntity>
}
