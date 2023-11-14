package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityRelationOneToOneApproachBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone.AddressForOneToOneRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetoone.StudentWithOneToOneRelationEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "RelationOneToOneApproachActivity"

class RelationOneToOneApproachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelationOneToOneApproachBinding
    private lateinit var db: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationOneToOneApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase.getInstance(applicationContext)

        binding.btnCreateStudentForOneToOneRelation.setOnClickListener {
            createStudentForOneToOneRelation()
        }

        binding.btnCreateAddressForOneToOneRelation.setOnClickListener {
            createAddressForOneToOneRelation()
        }

        binding.btnGetAllStudentsWithOneToOneRelationship.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val students =
                    db.studentWithOneToOneRelationDao().getAllStudentsWithAddresses()

                students.forEach {
                    Log.d(TAG, "${it.student.studentId}, ${it.address}")
                }
            }
        }
    }

    private fun createStudentForOneToOneRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithOneToOneRelationDao().saveStudent(
                buildOneToOneStudent()
            )
        }
    }

    private fun createAddressForOneToOneRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithOneToOneRelationDao().saveAddress(
                buildOneToOneAddress()
            )
        }
    }

    private fun buildOneToOneStudent(): StudentWithOneToOneRelationEntity {
        val id = Date().time
        return StudentWithOneToOneRelationEntity(
            id,
            "MyFirstName",
            "MyLastName"
        )
    }

    private fun buildOneToOneAddress(): AddressForOneToOneRelationEntity {
        val id = Date().time

        val studentId = binding.etStudentIdInput.text.toString().toLong()

        return AddressForOneToOneRelationEntity(
            id,
            "1234",
            "Paunch Burger",
            "RD",
            "Pawnee",
            "IN",
            "98765",
            studentId
        )
    }
}
