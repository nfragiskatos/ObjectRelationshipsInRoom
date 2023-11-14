package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "addressForOneToOneRelation",
    foreignKeys = [
        ForeignKey(
            entity = StudentWithOneToOneRelationEntity::class,
            parentColumns = arrayOf("studentId"),
            childColumns = arrayOf("studentOwnerId"),
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [Index(value = ["studentOwnerId"], unique = true)]
)
data class AddressForOneToOneRelationEntity(

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
