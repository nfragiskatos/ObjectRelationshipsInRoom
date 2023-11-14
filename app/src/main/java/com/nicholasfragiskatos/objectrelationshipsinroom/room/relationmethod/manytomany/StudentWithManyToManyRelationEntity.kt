package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithManyToManyRelation")
data class StudentWithManyToManyRelationEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0,
    val firstName: String,
    val lastName: String
)
