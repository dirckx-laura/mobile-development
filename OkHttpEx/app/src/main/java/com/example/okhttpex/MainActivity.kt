package com.example.okhttpex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var getbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getbtn = findViewById(R.id.getbtn)

        getbtn.setOnClickListener{
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("OUR_APP", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use{
                        if(!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers){
                            Log.d("OUR_APP", "$name: $value")
                        }

                        Log.d("OUR_APP", response.body!!.string())
                    }
                }
            })
        }

    }





}