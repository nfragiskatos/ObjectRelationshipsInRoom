package com.nicholasfragiskatos.objectrelationshipsinroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nicholasfragiskatos.objectrelationshipsinroom.databinding.ActivityMainBinding
import com.nicholasfragiskatos.objectrelationshipsinroom.room.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabase.getInstance(applicationContext)

        lifecycleScope.launch(Dispatchers.IO) {
        }

        binding.btnGoToEmbeddedApproachScreen.setOnClickListener {
            val intent = Intent(this, EmbeddedApproachActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToTypeConverterApproachScreen.setOnClickListener {
            val intent = Intent(this, TypeConverterApproachActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRelationOneToOneApproachScreen.setOnClickListener {
            val intent = Intent(this, RelationOneToOneApproachActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRelationScreen.setOnClickListener {
            val intent = Intent(this, RelationActivity::class.java)
            startActivity(intent)
        }
    }
}
