package com.example.books_cite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.books_cite.databinding.FragmentHomeBinding
import com.example.books_cite.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


private lateinit var firebaseAuth: FirebaseAuth
private lateinit var binding: FragmentProfileBinding


class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        var email=firebaseAuth.currentUser?.email.toString()
        var userName = email.substringBefore("@")
        binding.textView12.text = userName
      binding.buttonLogout.setOnClickListener{
            firebaseAuth.signOut()
            val intent= Intent(context,LoginActivity::class.java);
            startActivity(intent)
        }
    }


}