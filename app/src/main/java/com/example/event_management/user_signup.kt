package com.example.event_management

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class user_signup : AppCompatActivity() {
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signup)
        dbHelper = DbHelper(this)
        val username: TextView = findViewById(R.id.username_usersignup)
        val email: TextView = findViewById(R.id.email_usersignup)
        val password: TextView = findViewById(R.id.password_usersignup)
        val confirm_password: TextView = findViewById(R.id.confirm_password_usersignup)
        val signupbutton: Button = findViewById(R.id.register_button_usersignup)
        signupbutton.setOnClickListener {
            val username = username.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            val confirmPassword = confirm_password.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Insert the user data into the database
                val newRowId = dbHelper.insertUserData(username, email, password)

                if (newRowId != -1L) {
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                    // Optionally, you can navigate to another activity after successful registration
                    val intent=Intent(this@user_signup,user_login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close() // Close the database connection when the activity is destroyed
    }


}
