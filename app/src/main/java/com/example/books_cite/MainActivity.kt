package com.example.books_cite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var button : Button
        button = findViewById(R.id.buttonGetStarted);
        button.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)



        })

    }


}