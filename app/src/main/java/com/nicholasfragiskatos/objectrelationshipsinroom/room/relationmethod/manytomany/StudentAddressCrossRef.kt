package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "addressId"])
data class StudentAddressCrossRef(
    val studentId: Long,
    val addressId: Long
)
