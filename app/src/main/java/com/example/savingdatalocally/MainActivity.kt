package com.example.savingdatalocally

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var userName : EditText
    lateinit var userMessage : EditText
    lateinit var counter : Button
    lateinit var remember : CheckBox

    var count : Int = 0
    var name : String? = null
    var message : String? = null
    var isChecked : Boolean? = null

    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.editTextName)
        userMessage = findViewById(R.id.editTextMessage)
        counter = findViewById(R.id.button)
        remember = findViewById(R.id.checkBox)

        counter.setOnClickListener {
            count = count + 1
            counter.setText("" + count)
            Log.d("count data",count.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        savData()
    }


    fun savData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = userName.text.toString()
        message = userMessage.text.toString()
        isChecked = remember.isChecked

        val editor = sharedPreferences.edit()

        editor.putString("Key Name", name)
        editor.putString("Key Message", message)
        editor.putInt("Key count", count)
        editor.putBoolean("Key remember", isChecked!!)

        editor.apply()

        Toast.makeText(this, "User data downlad successfully", Toast.LENGTH_LONG).show()
    }

    fun retrieveData() {
        sharedPreferences  = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("Key Name", null)
        message = sharedPreferences.getString("Key Message", null)
        count = sharedPreferences.getInt("Key count", 0)
        isChecked = sharedPreferences.getBoolean("Key remember", false)

        userName.setText(name)
        userMessage.setText(message)
        counter.setText("" + count)
        remember.isChecked = isChecked!!

    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }
}