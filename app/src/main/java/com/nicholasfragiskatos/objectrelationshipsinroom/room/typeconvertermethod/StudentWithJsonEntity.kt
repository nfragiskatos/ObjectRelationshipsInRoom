package com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithJson")
data class StudentWithJsonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "address_json")
    val address: AddressForJson,
)
