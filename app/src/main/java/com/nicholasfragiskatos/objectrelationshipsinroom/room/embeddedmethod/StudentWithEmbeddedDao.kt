package com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentWithEmbeddedDao {

    @Insert
    fun saveStudentWithEmbedded(student: StudentWithEmbeddedEntity)

    @Query("SELECT * FROM studentWithEmbedded")
    fun getAllStudentsWithEmbedded(): List<StudentWithEmbeddedEntity>
}