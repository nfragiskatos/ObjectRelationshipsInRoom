package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityRelationOneToManyApproachBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.AddressForOneToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.StudentWithOneToManyRelationEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "RelationOneToManyApproachActivity"

class RelationOneToManyApproachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelationOneToManyApproachBinding
    private lateinit var db: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationOneToManyApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase.getInstance(applicationContext)

        binding.btnCreateStudentForOneToManyRelation.setOnClickListener {
            createStudentForOneToManyRelation()
        }

        binding.btnCreateAddressForOneToManyRelation.setOnClickListener {
            createAddressForOneToManyRelation()
        }

        binding.btnGetAllStudentsWithOneToManyRelationship.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val students =
                    db.studentWithOneToManyRelationDao().getAllStudentsWithAddresses()

                students.forEach {
                    Log.d(TAG, "${it.student.studentId}, ${it.address}")
                }
            }
        }
    }

    private fun createStudentForOneToManyRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithOneToManyRelationDao().saveStudent(
                buildOneToManyStudent()
            )
        }
    }

    private fun createAddressForOneToManyRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithOneToManyRelationDao().saveAddress(
                buildOneToManyAddress()
            )
        }
    }

    private fun buildOneToManyStudent(): StudentWithOneToManyRelationEntity {
        val id = Date().time
        return StudentWithOneToManyRelationEntity(
            id,
            "MyFirstName",
            "MyLastName"
        )
    }

    private fun buildOneToManyAddress(): AddressForOneToManyRelationEntity {
        val id = Date().time

        val studentId = binding.etStudentIdInput.text.toString().toLong()

        return AddressForOneToManyRelationEntity(
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
