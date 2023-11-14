package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StudentWithOneToOneRelationDao {

    @Insert
    fun saveStudent(student: StudentWithOneToOneRelationEntity): Long

    @Insert
    fun saveAddress(address: AddressForOneToOneRelationEntity): Long

    @Transaction
    @Query("SELECT * from studentWithOneToOneRelation")
    fun getAllStudentsWithAddresses(): List<StudentWithAddressOneToOneIntermediate>
}
