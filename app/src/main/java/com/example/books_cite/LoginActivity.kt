package com.example.books_cite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.books_cite.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var textView: View
        textView = findViewById(R.id.textView2);
        firebaseAuth = FirebaseAuth.getInstance()
        textView.setOnClickListener(View.OnClickListener {
            val intent= Intent(this,RegisterActivity::class.java);
            startActivity(intent);
            println("Alo Bolan")
        })

        binding.buttonLogin.setOnClickListener(View.OnClickListener{
            val email = binding.editTextEmailLog.text.toString()
            val pass = binding.editTextPassLog.text.toString()
            println("Alo Bolan")
            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val e = it.getException()
                        Log.e("LoginActivity", "Failed Registration", e);
                        val intent = Intent(this, ContentActivity::class.java)
                        startActivity(intent)
                    } else if (!it.isSuccessful) {

                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
                    }

            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
                Log.i("Alo","Bolan")

            }

        })

    }
    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, ContentActivity::class.java)
            startActivity(intent)
        }
    }
}