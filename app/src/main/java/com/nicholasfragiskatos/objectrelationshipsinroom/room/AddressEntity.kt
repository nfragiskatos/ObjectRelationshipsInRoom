package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val houseNumber: String,
    val streetName: String,
    val streetType: String,
    val city: String,
    val state: String,
    @ColumnInfo(name = "postal_code")
    val postalCode: String,
)
