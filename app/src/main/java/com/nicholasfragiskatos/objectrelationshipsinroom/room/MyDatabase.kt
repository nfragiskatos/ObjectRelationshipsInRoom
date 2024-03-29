package com.nicholasfragiskatos.objectrelationshipsinroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.AddressForManyToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.StudentAddressCrossRef
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.StudentWithManyToManyRelationDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.StudentWithManyToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.AddressForOneToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.StudentWithOneToManyRelationDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.StudentWithOneToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone.AddressForOneToOneRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone.StudentWithOneToOneRelationDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone.StudentWithOneToOneRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.MyRoomTypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonDao
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Database(
    entities = [
        StudentWithManyToManyRelationEntity::class,
        AddressForManyToManyRelationEntity::class,
        StudentAddressCrossRef::class,
        StudentWithOneToManyRelationEntity::class,
        AddressForOneToManyRelationEntity::class,
        StudentWithOneToOneRelationEntity::class,
        AddressForOneToOneRelationEntity::class,
        StudentWithJsonEntity::class,
        StudentWithEmbeddedEntity::class
    ],
    version = 1
)
@TypeConverters(MyRoomTypeConverters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentWithJsonDao(): StudentWithJsonDao

    abstract fun studentWithEmbeddedDao(): StudentWithEmbeddedDao

    abstract fun studentWithOneToOneRelationDao(): StudentWithOneToOneRelationDao

    abstract fun studentWithOneToManyRelationDao(): StudentWithOneToManyRelationDao

    abstract fun studentWithManyToManyRelationDao(): StudentWithManyToManyRelationDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private val typeConverters: MyRoomTypeConverters = MyRoomTypeConverters(moshi)

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        MyDatabase::class.java,
                        "my-database"
                    ).addTypeConverter(typeConverters).build()

                    INSTANCE = instance
                }
                return INSTANCE!!
            }
        }
    }
}
