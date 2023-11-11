package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [StudentEntity::class, AddressEntity::class, StudentWithJsonEntity::class],
    version = 1,
)
@TypeConverters(MyRoomTypeConverters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    abstract fun studentWithJsonDao(): StudentWithJsonDao
}
