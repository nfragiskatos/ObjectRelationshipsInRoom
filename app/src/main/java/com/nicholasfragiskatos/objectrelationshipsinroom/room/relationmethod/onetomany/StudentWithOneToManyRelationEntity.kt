package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithOneToManyRelation")
data class StudentWithOneToManyRelationEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0,
    val firstName: String,
    val lastName: String
)
