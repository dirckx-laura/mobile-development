package com.example.jsoncallclaxon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var editText1 : EditText
    lateinit var button1 : Button
    val parser: Parser = Parser.default()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editText1)
        editText1.setText("students.json")
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener{_ ->
            val json = application.assets.open(editText1.text.toString())
            val array = parser.parse(json) as JsonArray<JsonObject>
            val names = array.string("last")

            Toast.makeText(applicationContext, names.toString(), Toast.LENGTH_LONG).show()
        val stanford = array.filter { s ->
            s.obj("schoolResults")?.string("location") == "Stanford"
        }.map {
            s -> s.string("last")
        }
            Toast.makeText(applicationContext, stanford.toString(), Toast.LENGTH_LONG).show()
        }

    }
}