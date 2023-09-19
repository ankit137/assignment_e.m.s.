package com.example.event_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DbHelper(this)
        dbHelper.writableDatabase
        val userbutton:TextView=findViewById(R.id.userbutton)
        val vendorbutton:TextView=findViewById(R.id.vendorbutton)
        val adminbutton:TextView=findViewById(R.id.adminbutton)
        userbutton.setOnClickListener{
            val intent=Intent(this@MainActivity,user_login::class.java)
            startActivity(intent)
        }
        vendorbutton.setOnClickListener{
            val intent=Intent(this@MainActivity,vendor_login::class.java)
            startActivity(intent)
        }
        adminbutton.setOnClickListener{
            val intent=Intent(this@MainActivity,admin_login::class.java)
            startActivity(intent)
        }
    }
}