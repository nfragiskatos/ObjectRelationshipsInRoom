package com.nicholasfragiskatos.objectrelationshipsinroom.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class, AddressEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}
