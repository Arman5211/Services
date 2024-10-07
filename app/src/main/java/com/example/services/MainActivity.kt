package com.example.services

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("my_prefs",Context.MODE_PRIVATE)

        //for foreground service
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.
        POST_NOTIFICATIONS),200)

        findViewById<Button>(R.id.button1).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            intent.action = Actions.START.toString()
            if (prefs.getString("componentName", null) != null) {
                val serviceComponentname = startService(intent)
                val editor = prefs.edit()
                editor.putString("componentName", serviceComponentname.toString())
                editor.apply()
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            val intent = Intent(this,MyService::class.java)
            intent.action = Actions.STOP.toString()
            if (prefs.getString("componentName",null) != null){
                val serviceComponentname = startService(intent)
                val editor = prefs.edit()
                editor.putString("componentName",serviceComponentname.toString())
                editor.clear()
                editor.apply()

            }

        }

    }
}