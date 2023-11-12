package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentWithRelationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String

//    val address: AddressEntity,
)
