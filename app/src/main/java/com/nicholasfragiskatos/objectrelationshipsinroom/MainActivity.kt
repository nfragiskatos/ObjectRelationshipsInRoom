package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityMainBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db: MyDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "my-database",
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val allStudents = db.studentDao().getAllStudents()
        }
    }
}
