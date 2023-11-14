package com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StudentWithManyToManyRelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(student: StudentWithManyToManyRelationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAddress(address: AddressForManyToManyRelationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudentAddressCrossRef(ref: StudentAddressCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun saveAddress(address: AddressForManyToManyRelationEntity, studentId: Long) {
        saveAddress(address)
        saveStudentAddressCrossRef(StudentAddressCrossRef(studentId, address.addressId))
    }

    @Query("DELETE FROM studentWithManyToManyRelation where studentId = :studentId")
    fun deleteStudent(studentId: Long): Int

    @Query("DELETE FROM addressForManyToManyRelation where addressId = :addressId")
    fun deleteAddress(addressId: Long): Int

    @Transaction
    @Query("SELECT * from studentWithManyToManyRelation")
    fun getAllStudentsWithAddresses(): List<StudentWithAddressManyToManyIntermediate>
}
