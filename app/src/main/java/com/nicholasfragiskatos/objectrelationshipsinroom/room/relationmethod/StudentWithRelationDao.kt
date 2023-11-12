package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudentWithRelationDao {

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<StudentWithRelationEntity>
}
