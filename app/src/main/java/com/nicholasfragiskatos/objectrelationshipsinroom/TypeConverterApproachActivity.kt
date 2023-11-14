package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityTypeConverterApproachBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.AddressForJson
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "TypeConverterApproachActivity"

class TypeConverterApproachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeConverterApproachBinding
    private lateinit var db: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeConverterApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase.getInstance(applicationContext)

        binding.btnCreateStudentWithJson.setOnClickListener {
            createStudentWithJsonEntity()
        }

        binding.btnGetStudentsWithJson.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val studentsWithJson = db.studentWithJsonDao().getAllStudentsWithJson()
                studentsWithJson.forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }
    }

    private fun createStudentWithJsonEntity() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithJsonDao().saveStudentWithJson(buildStudentWithJsonEntity())
        }
    }

    private fun buildStudentWithJsonEntity(): StudentWithJsonEntity {
        val id = Date().time

        val address = AddressForJson(
            id,
            "1234",
            "Paunch Burger",
            "RD",
            "Pawnee",
            "IN",
            "98765"
        )

        return StudentWithJsonEntity(
            id + 1,
            "MyFirstName",
            "MyLastName",
            address
        )
    }
}
