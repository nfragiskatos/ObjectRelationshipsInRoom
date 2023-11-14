package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithAddressOneToManyIntermediate(
    @Embedded
    val student: StudentWithOneToManyRelationEntity,

    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentOwnerId"
    )
    val address: List<AddressForOneToManyRelationEntity>?
)
