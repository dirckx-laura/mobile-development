package com.example.sqlliteprobeerselstudents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_STUDENTS)
    }




    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        onCreate(db)
    }

    fun allStudents(): ArrayList<String>{
        val studentsArrayList = ArrayList<String>()
        var name : String
        val selectQuery = "SELECT * FROM $TABLE_STUDENTS"
        val db = this.readableDatabase
        val c = db.rawQuery(selectQuery, null)
        if(c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(NAME))
                studentsArrayList.add(name)
            }while (c.moveToNext())
        }
        return studentsArrayList
    }

    fun addStudent(student: String) : Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, student)

        return db.insert(TABLE_STUDENTS, null, values)
    }


    companion object{
        var DATABASE_NAME = "student_database"
        private val DATABASE_VERSION = 4
        private val TABLE_STUDENTS = "students"
        private val KEY_ID = "id"
        private val NAME = "name"

        private val CREATE_TABLE_STUDENTS = ("CREATE TABLE "
                + com.example.sqlliteprobeerselstudents.DatabaseHelper.Companion.TABLE_STUDENTS + "(" + com.example.sqlliteprobeerselstudents.DatabaseHelper.Companion.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + com.example.sqlliteprobeerselstudents.DatabaseHelper.Companion.NAME + " TEXT );")
    }
}