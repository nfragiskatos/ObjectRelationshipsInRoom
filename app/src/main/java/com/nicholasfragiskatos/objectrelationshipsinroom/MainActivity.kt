package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityMainBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.AddressForEmbedded
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedEntity
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.AddressForJson
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.MyRoomTypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.typeconvertermethod.StudentWithJsonEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val typeConverters: MyRoomTypeConverters = MyRoomTypeConverters(moshi)
    private val db: MyDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "my-database",
        ).addTypeConverter(typeConverters).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val allStudents = db.studentDao().getAllStudents()
        }

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

        binding.btnCreateStudentWithEmbedded.setOnClickListener {
            createStudentWithEmbeddedEntity()
        }

        binding.btnGetStudentsWithEmbedded.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val studentsWithEmbedded =
                    db.studentWithEmbeddedDao().getAllStudentsWithEmbedded()

                studentsWithEmbedded.forEach {
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

    private fun createStudentWithEmbeddedEntity() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithEmbeddedDao().saveStudentWithEmbedded(buildStudentWithEmbeddedEntity())
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
            "98765",
        )

        return StudentWithJsonEntity(
            id + 1,
            "MyFirstName",
            "MyLastName",
            address,
        )
    }

    private fun buildStudentWithEmbeddedEntity(): StudentWithEmbeddedEntity {
        val id = Date().time
        val address = AddressForEmbedded(
            "1234",
            "Paunch Burger",
            "RD",
            "Pawnee",
            "IN",
            "98765"
        )

        return StudentWithEmbeddedEntity(
            id + 1,
            "MyFirstName",
            "MyLastName",
            address,
        )
    }
}
