package com.example.event_management
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "EVENT_MANAGEMENT.db" // Change this to your desired database name
        private const val DATABASE_VERSION = 1

        // Define the user_table and its columns
        private const val TABLE_USER = "user_table"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USER_NAME = "user_name"
        private const val COLUMN_USER_PASSWORD = "user_password"
        private const val COLUMN_USER_EMAIL = "user_email"

        // Define the admin_table and its columns
        private const val TABLE_ADMIN = "admin_table"
        private const val COLUMN_ADMIN_ID = "admin_id"
        private const val COLUMN_ADMIN_NAME = "admin_name"
        private const val COLUMN_ADMIN_PASSWORD = "admin_password"
        private const val COLUMN_ADMIN_EMAIL = "admin_email"
        private const val COLUMN_ADMIN_CATEGORY = "admin_category"

        // Define the vendor_table and its columns
        private const val TABLE_VENDOR = "vendor_table"
        private const val COLUMN_VENDOR_ID = "vendor_id"
        private const val COLUMN_VENDOR_NAME = "vendor_name"
        private const val COLUMN_VENDOR_PASSWORD = "vendor_password"
        private const val COLUMN_VENDOR_EMAIL = "vendor_email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the user_table with the specified columns
        val createUserTableQuery = "CREATE TABLE IF NOT EXISTS $TABLE_USER (" +
                "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USER_NAME TEXT NOT NULL," +
                "$COLUMN_USER_PASSWORD TEXT NOT NULL," +
                "$COLUMN_USER_EMAIL TEXT NOT NULL UNIQUE);"

        db.execSQL(createUserTableQuery)

        // Create the admin_table with the specified columns
        val createAdminTableQuery = "CREATE TABLE IF NOT EXISTS $TABLE_ADMIN (" +
                "$COLUMN_ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ADMIN_NAME TEXT NOT NULL," +
                "$COLUMN_ADMIN_PASSWORD TEXT NOT NULL," +
                "$COLUMN_ADMIN_EMAIL TEXT NOT NULL UNIQUE," +
                "$COLUMN_ADMIN_CATEGORY TEXT NOT NULL);"

        db.execSQL(createAdminTableQuery)

        // Create the vendor_table with the specified columns
        val createVendorTableQuery = "CREATE TABLE IF NOT EXISTS $TABLE_VENDOR (" +
                "$COLUMN_VENDOR_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_VENDOR_NAME TEXT NOT NULL," +
                "$COLUMN_VENDOR_PASSWORD TEXT NOT NULL," +
                "$COLUMN_VENDOR_EMAIL TEXT NOT NULL UNIQUE);"

        db.execSQL(createVendorTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades, if needed
        // This method is called when the DATABASE_VERSION is increased
    }
    fun insertUserData(username: String, email: String, password: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_USER_NAME, username)
            put(COLUMN_USER_EMAIL, email)
            put(COLUMN_USER_PASSWORD, password)
            put(COLUMN_USER_ID,0)
            // Add any additional columns and values as needed
        }

        val db = writableDatabase
        val newRowId = db.insert(TABLE_USER, null, values)
        db.close()
        return newRowId
    }
    fun insertAdminData(username: String, email: String, password: String, selectedCategory:String): Long {
        val values = ContentValues().apply {
            put(COLUMN_ADMIN_NAME, username)
            put(COLUMN_ADMIN_EMAIL, email)
            put(COLUMN_ADMIN_PASSWORD, password)
            put(COLUMN_ADMIN_CATEGORY,selectedCategory)
            // Add any additional columns and values as needed
        }

        val db = writableDatabase
        val newRowId = db.insert(TABLE_ADMIN, null, values)
        db.close()
        return newRowId
    }

}
