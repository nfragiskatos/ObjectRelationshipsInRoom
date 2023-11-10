package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,

    val address: AddressEntity,
)
