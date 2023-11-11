package com.nicholasfragiskatos.objectrelationshipsinroom.room

data class Address(
    val id: Long = 0,
    val houseNumber: String,
    val streetName: String,
    val streetType: String,
    val city: String,
    val state: String,
    val postalCode: String,
)
