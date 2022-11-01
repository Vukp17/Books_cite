package com.example.books_cite

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
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
        replaceFragment(HomeFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.edit -> replaceFragment(ContentFragment())
                R.id.profile -> replaceFragment(ProfileFragment())

                else ->{



                }

            }

            true

        }


    }


    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()


    }
}