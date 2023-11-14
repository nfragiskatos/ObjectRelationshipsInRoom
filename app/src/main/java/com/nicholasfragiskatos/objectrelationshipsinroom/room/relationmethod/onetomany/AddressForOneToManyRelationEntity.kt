package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "addressForOneToManyRelation",
    foreignKeys = [
        ForeignKey(
            entity = StudentWithOneToManyRelationEntity::class,
            parentColumns = arrayOf("studentId"),
            childColumns = arrayOf("studentOwnerId")
//            onDelete = ForeignKey.CASCADE

        )
    ]
)
data class AddressForOneToManyRelationEntity(

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
