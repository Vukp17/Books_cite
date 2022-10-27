package com.example.books_cite

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books_cite.databinding.ActivityContentBinding
import com.example.books_cite.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class HomeFragment : Fragment() {


    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var recyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.recyclerMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        binding.recyclerMain.setHasFixedSize(true)
        binding.recyclerMain.setBackgroundColor(Color.rgb(220,204,164))
        fetch()

        button.setOnClickListener{
            firebaseAuth.signOut()
            val intent= Intent(context,LoginActivity::class.java);
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
                getActivity()?.runOnUiThread( Runnable {
                  binding.recyclerMain.adapter = ContentAdapter(feed)
                });
            }

        })
    }


}