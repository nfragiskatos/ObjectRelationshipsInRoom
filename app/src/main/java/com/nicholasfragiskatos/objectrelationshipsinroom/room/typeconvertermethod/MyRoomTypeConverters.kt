package com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class MyRoomTypeConverters(private val moshi: Moshi) {

    @TypeConverter
    fun fromAddressJson(json: String): AddressForJson {
        return moshi.adapter(AddressForJson::class.java).fromJson(json)!!
    }

    @TypeConverter
    fun toAddressJson(address: AddressForJson): String {
        return moshi.adapter(AddressForJson::class.java).toJson(address)
    }
}
