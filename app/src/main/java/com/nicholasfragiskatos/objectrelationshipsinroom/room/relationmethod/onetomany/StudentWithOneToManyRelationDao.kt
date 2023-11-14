package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StudentWithOneToManyRelationDao {

    @Insert
    fun saveStudent(student: StudentWithOneToManyRelationEntity): Long

    @Insert
    fun saveAddress(address: AddressForOneToManyRelationEntity): Long

    @Transaction
    @Query("SELECT * from studentWithOneToManyRelation")
    fun getAllStudentsWithAddresses(): List<StudentWithAddressOneToManyIntermediate>
}
