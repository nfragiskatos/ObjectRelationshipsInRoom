package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityRelationBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.AddressForManyToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.StudentWithManyToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.AddressForOneToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.onetomany.StudentWithOneToManyRelationEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "RelationActivity"

class RelationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelationBinding
    private lateinit var db: MyDatabase
    private var lastAddress: AddressForManyToManyRelationEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase.getInstance(applicationContext)

        setupOneToManyClickListeners()
        setupManyToManyClickListeners()
    }

    private fun setupOneToManyClickListeners() {
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

    private fun setupManyToManyClickListeners() {
        binding.btnCreateStudentForManyToManyRelation.setOnClickListener {
            createStudentForManyToManyRelation()
        }

        binding.btnCreateAddressForManyToManyRelation.setOnClickListener {
            createAddressForManyToManyRelation()
        }

        binding.btnGetAllStudentsWithManyToManyRelationship.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val students =
                    db.studentWithManyToManyRelationDao().getAllStudentsWithAddresses()

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

    private fun createStudentForManyToManyRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithManyToManyRelationDao().saveStudent(
                buildManyToManyStudent()
            )
        }
    }

    private fun createAddressForManyToManyRelation() {
        lifecycleScope.launch(Dispatchers.IO) {
            val studentId = binding.etStudentIdInput.text.toString().toLong()

            val reuse = binding.swReuseLastAddress.isChecked

            val address =
                if (reuse && lastAddress != null) lastAddress else buildManyToManyAddress()

            lastAddress = address

            address?.let {
                db.studentWithManyToManyRelationDao().saveAddress(
                    it,
                    studentId
                )
            }
        }
    }

    private fun buildManyToManyStudent(): StudentWithManyToManyRelationEntity {
        val id = Date().time
        return StudentWithManyToManyRelationEntity(
            id,
            "MyFirstName",
            "MyLastName"
        )
    }

    private fun buildManyToManyAddress(): AddressForManyToManyRelationEntity {
        val id = Date().time

        return AddressForManyToManyRelationEntity(
            id,
            "1234",
            "Paunch Burger",
            "RD",
            "Pawnee",
            "IN",
            "98765"
        )
    }
}
