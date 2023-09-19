package com.example.event_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class user_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val new_user:TextView=findViewById(R.id.new_user)
        val dbHelper = DbHelper(this)
        new_user.setOnClickListener{
            val intent=Intent(this@user_login,user_signup::class.java)
            startActivity(intent)
        }
    }
}