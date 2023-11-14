package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addressForManyToManyRelation")
data class AddressForManyToManyRelationEntity(

    @PrimaryKey(autoGenerate = true)
    val addressId: Long = 0,
    val houseNumber: String,
    val streetName: String,
    val streetType: String,
    val city: String,
    val state: String,
    @ColumnInfo(name = "postal_code")
    val postalCode: String
)
