package com.example.event_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class admin_signup : AppCompatActivity() {
    private lateinit var dbHelper: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)
        dbHelper = DbHelper(this)

        val username: EditText = findViewById(R.id.username_adminsignup)
        val email: EditText = findViewById(R.id.email_adminsignup)
        val password: EditText = findViewById(R.id.password_adminsignup)
        val confirmPassword: EditText = findViewById(R.id.confirm_password_adminsignup)
        val categoryDropdown: Spinner = findViewById(R.id.category_dropdown)
        val signupButton: Button = findViewById(R.id.register_button_adminsignup)

        signupButton.setOnClickListener {
            val usernameStr = username.text.toString()
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            val confirmPasswordStr = confirmPassword.text.toString()
            val selectedCategory =" categoryDropdown.selectedItem.toString()"

            if (usernameStr.isEmpty() || emailStr.isEmpty() || passwordStr.isEmpty() || confirmPasswordStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (passwordStr != confirmPasswordStr) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                // Insert the admin data into the database
                try {
                    val newRowId = dbHelper.insertAdminData(usernameStr, emailStr, passwordStr, selectedCategory)
                    if (newRowId != -1L) {
                        Toast.makeText(this, "Admin registered successfully", Toast.LENGTH_SHORT).show()
                        val intent=Intent(this@admin_signup,admin_login::class.java)
                        startActivity(intent)
                        finish()
                        // Optionally, you can navigate to another activity after successful registration
                    } else {
                        Toast.makeText(this, "Failed to register admin", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close() // Close the database connection when the activity is destroyed
    }
}