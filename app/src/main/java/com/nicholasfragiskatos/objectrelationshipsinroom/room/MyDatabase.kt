package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.AddressForRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.StudentWithRelationDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.StudentWithRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.MyRoomTypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonEntity

@Database(
    entities = [StudentWithRelationEntity::class, AddressForRelationEntity::class, StudentWithJsonEntity::class, StudentWithEmbeddedEntity::class],
    version = 1
)
@TypeConverters(MyRoomTypeConverters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentWithRelationDao

    abstract fun studentWithJsonDao(): StudentWithJsonDao

    abstract fun studentWithEmbeddedDao(): StudentWithEmbeddedDao
}
