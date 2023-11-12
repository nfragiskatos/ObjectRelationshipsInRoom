package com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentWithEmbedded")
data class StudentWithEmbeddedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @Embedded
    val address: AddressForEmbedded
)
