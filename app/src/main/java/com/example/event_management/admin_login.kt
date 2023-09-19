package com.example.event_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class admin_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        val new_admin:TextView=findViewById(R.id.admin_signup_button)
        new_admin.setOnClickListener{
            val intent=Intent(this@admin_login,admin_signup::class.java)
            startActivity(intent)
        }
    }
}