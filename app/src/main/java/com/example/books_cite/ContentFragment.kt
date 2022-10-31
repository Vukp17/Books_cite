package com.example.books_cite

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books_cite.databinding.ActivityContentBinding
import com.example.books_cite.databinding.FragmentContentBinding
import com.example.books_cite.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private  lateinit var binding: FragmentContentBinding
private lateinit var firebaseAuth: FirebaseAuth
/**
 * A simple [Fragment] subclass.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCite.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerCite.setHasFixedSize(true)
        binding.recyclerCite.adapter=CiteAdapter()
        binding.recyclerCite.setBackgroundColor(Color.rgb(220, 204, 164))
        var database = FirebaseDatabase.getInstance().reference
        firebaseAuth = FirebaseAuth.getInstance()

         var cites = mutableListOf<String>()
        binding.buttonAdd.setOnClickListener{
            var citesText=binding.editTextCite.text.toString()
            cites.add(citesText)

            var email=firebaseAuth.currentUser?.email.toString()
            var userName = email.substringBefore("@")
            database.child(userName).setValue(cites).addOnCompleteListener{
                if (it.isSuccessful) {
                    val e = it.getException()
                    Log.e("LoginActivity", "Failed Registration", e);


                } else if (!it.isSuccessful) {

                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }

            }

            binding.editTextCite.text.clear()
        }

    }

}