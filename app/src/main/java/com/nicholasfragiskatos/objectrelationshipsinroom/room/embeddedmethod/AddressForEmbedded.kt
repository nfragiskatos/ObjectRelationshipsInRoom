package com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod

import androidx.room.ColumnInfo

data class AddressForEmbedded(
    @ColumnInfo(name = "house_number_embedded")
    val houseNumber: String,

    @ColumnInfo(name = "street_name_embedded")
    val streetName: String,

    @ColumnInfo(name = "street_type_embedded")
    val streetType: String,

    @ColumnInfo(name = "city_embedded")
    val city: String,

    @ColumnInfo(name = "state_embedded")
    val state: String,

    @ColumnInfo(name = "postal_code_embedded")
    val postalCode: String,
)