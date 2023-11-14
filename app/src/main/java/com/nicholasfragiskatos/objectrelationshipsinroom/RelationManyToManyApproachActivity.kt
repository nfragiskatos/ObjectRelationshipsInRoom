package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityRelationManyToManyApproachBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.AddressForManyToManyRelationEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.relationmethod.manytomany.StudentWithManyToManyRelationEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "RelationManyToManyApproachActivity"

class RelationManyToManyApproachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelationManyToManyApproachBinding
    private lateinit var db: MyDatabase
    private var lastAddress: AddressForManyToManyRelationEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationManyToManyApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase.getInstance(applicationContext)

        setupManyToManyClickListeners()
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

        binding.btnDeleteStudent.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val id = binding.etStudentIdInput.text.toString().toLong()

                val deleteStudent = db.studentWithManyToManyRelationDao().deleteStudent(id)
                Log.d(TAG, "Rows Deleted: $deleteStudent")
            }
        }

        binding.btnDeleteAddress.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val id = binding.etAddressId.text.toString().toLong()

                val deleteAddress = db.studentWithManyToManyRelationDao().deleteAddress(id)
                Log.d(TAG, "Rows Deleted: $deleteAddress")
            }
        }
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
