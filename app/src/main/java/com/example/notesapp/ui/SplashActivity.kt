package com.example.notesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notesapp.MainActivity
import com.example.notesapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        Handler(Looper.getMainLooper())
            .postDelayed({
              val intent= Intent(this,MainActivity::class.java)
              startActivity(intent)
              finish()
            },2000)
    }
}