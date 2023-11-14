package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithAddressManyToManyIntermediate(
    @Embedded
    val student: StudentWithManyToManyRelationEntity,

    @Relation(
        parentColumn = "studentId",
        entityColumn = "addressId",
        associateBy = Junction(StudentAddressCrossRef::class)
    )
    val address: List<AddressForManyToManyRelationEntity>?
)
