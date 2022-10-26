package com.example.books_cite

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books_cite.databinding.ActivityContentBinding
import com.example.books_cite.databinding.ActivityLoginBinding
import com.example.books_cite.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class ContentActivity : AppCompatActivity() {
    lateinit var textView:TextView
    lateinit var button: Button
    lateinit var recyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityContentBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        recyclerView=findViewById(R.id.recyclerMain)
        button=findViewById(R.id.buttonLogout)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        recyclerView.setHasFixedSize(true)
        recyclerView.setBackgroundColor(Color.rgb(220,204,164))
        fetch()




        button.setOnClickListener{
            firebaseAuth.signOut()
            val intent= Intent(this,LoginActivity::class.java);
            startActivity(intent)
        }
    }

    private fun fetch() {

        val  request = Request.Builder()
            .url("https://hapi-books.p.rapidapi.com/month/2022/3")
            .get()
            .addHeader("X-RapidAPI-Key", "29e743a9d9msh5fb3118d47deef9p1afb17jsn2c2a13901257")
            .addHeader("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("EROR")

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson= GsonBuilder().create()

                val feed= gson.fromJson(body,library::class.java)
                runOnUiThread {
                    recyclerView.adapter = ContentAdapter(feed)
                }
            }

        })
    }

}