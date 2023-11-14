package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithOneToOneRelation")
data class StudentWithOneToOneRelationEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0,
    val firstName: String,
    val lastName: String
)
