package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "studentAddressCrossRef",
    primaryKeys = ["studentId", "addressId"],

    foreignKeys = [
        ForeignKey(
            entity = StudentWithManyToManyRelationEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AddressForManyToManyRelationEntity::class,
            parentColumns = ["addressId"],
            childColumns = ["addressId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentAddressCrossRef(
    val studentId: Long,
    val addressId: Long
)
