package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithAddressOneToOneIntermediate(
    @Embedded
    val student: StudentWithOneToOneRelationEntity,

    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentOwnerId"
    )
    val address: AddressForOneToOneRelationEntity?
)
