package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressForRelationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val houseNumber: String,
    val streetName: String,
    val streetType: String,
    val city: String,
    val state: String,
    @ColumnInfo(name = "postal_code")
    val postalCode: String,

    val studentOwnerId: Long
)
