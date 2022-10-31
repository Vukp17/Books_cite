package com.example.books_cite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.books_cite.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

 private lateinit var firebaseAuth:FirebaseAuth
 private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseReference: DatabaseReference



    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lateinit var textView: TextView
        textView = findViewById(R.id.textView5);
        firebaseAuth = FirebaseAuth.getInstance()
        val uid= firebaseAuth.currentUser?.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        textView.setOnClickListener(View.OnClickListener {
            val intent= Intent(this,LoginActivity::class.java);
            startActivity(intent);


        })
        binding.buttonRegister.setOnClickListener(View.OnClickListener{
            val email= binding.editTextEmail.text.toString()
            val pass= binding.editTextTextPassword.text.toString()
            val confirmPass=binding.editTextTextPasswordRe.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                    if (pass == confirmPass) {

                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            } else if  (!it.isSuccessful){
                                val e = it.getException()
                                Log.e("LoginActivity", "Failed Registration", e);
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                            }
                        }
                    } else {
                        Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "aLObolan !!", Toast.LENGTH_SHORT).show()

                }


        })
    }

}