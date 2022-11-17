package com.example.tasktracker


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make the launcher screen as first fragment

        supportFragmentManager.beginTransaction().replace(R.id.nav_container,FirstFragment()).commit()

    }
}
