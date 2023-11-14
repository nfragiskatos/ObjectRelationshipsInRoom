package com.nicholasfragiskatos.objectrelationshipsinroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityEmbeddedApproachBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.AddressForEmbedded
import com.nicholasfragiskatos.objectrelationshipsinroom.room.embeddedmethod.StudentWithEmbeddedEntity
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EmbeddedApproachActivity"

class EmbeddedApproachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmbeddedApproachBinding
    private lateinit var db: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmbeddedApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase.getInstance(applicationContext)

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

    private fun createStudentWithEmbeddedEntity() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.studentWithEmbeddedDao().saveStudentWithEmbedded(buildStudentWithEmbeddedEntity())
        }
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
            address
        )
    }
}
