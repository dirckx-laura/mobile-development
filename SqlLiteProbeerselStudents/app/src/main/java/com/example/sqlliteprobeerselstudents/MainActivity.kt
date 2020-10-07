package com.example.sqlliteprobeerselstudents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnStore: Button
    private var btnGetal : Button? = null
    private var etname : EditText? = null
    private var databaseHelper: DatabaseHelper? = null
    private var tvnames: TextView? = null
    private var arrayList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        tvnames = findViewById<TextView>(R.id.tvname)
        btnStore = findViewById<Button>(R.id.btnstore)
        btnGetal = findViewById<Button>(R.id.btnget)
        etname = findViewById<EditText>(R.id.etname)

        btnStore.setOnClickListener{
            val name = etname!!.text.toString()
            databaseHelper!!.addStudent(name)
            etname!!.setText("")
            Toast.makeText(this, "$name stored!", Toast.LENGTH_LONG).show()
        }

        btnGetal!!.setOnClickListener{
            arrayList = databaseHelper!!.allStudents()
            if(arrayList!!.size > 0){
                var txt = ""
                for (i in arrayList!!.indices){
                    txt += "\n" + arrayList!![i]
                }
                tvnames!!.text = txt
            }
        }
    }

}