package com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class MyRoomTypeConverters(private val moshi: Moshi) {

    @TypeConverter
    fun fromAddressJson(json: String): Address {
        return moshi.adapter(Address::class.java).fromJson(json)!!
    }

    @TypeConverter
    fun toAddressJson(address: Address): String {
        return moshi.adapter(Address::class.java).toJson(address)
    }
}
