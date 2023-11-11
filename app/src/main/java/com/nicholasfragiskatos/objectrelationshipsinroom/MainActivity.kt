package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityMainBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.Address
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyRoomTypeConverters
import com.nicholasfragiskatos.objectrelationshipsinroom.room.StudentWithJsonEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

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
            createStudentWithJson()
        }
    }

    fun createStudentWithJson() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithJsonDao().saveStudentWithJson(createStudentEntityWithJson())
        }
    }

    fun createStudentEntityWithJson(): StudentWithJsonEntity {
        val id = Date().time.toLong()

        val address = Address(
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
}
