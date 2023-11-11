package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithJson")
data class StudentWithJsonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,

    val address: Address,
)
