package com.example.books_cite

import android.annotation.SuppressLint
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso




class ContentAdapter(val library: library):RecyclerView.Adapter<ContentAdapter.CustomViewHolder>(){
    //broj knjiga
    class CustomViewHolder(val view:View): RecyclerView.ViewHolder(view){
        private val textView: TextView = view.findViewById(R.id.textView_book_title)

        fun bind(word: String) {
            textView.text = word
        }
    }
    override fun getItemCount(): Int {
        return library.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layourInflater= LayoutInflater.from(parent?.context)
        val bookForRow= layourInflater.inflate(R.layout.books_row,parent,false)
        return CustomViewHolder(bookForRow)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
     val title=library.get(position).name
        holder.bind(title)
        val coverImage = holder?.view?.findViewById<ImageView>(R.id.imageView3)
        Picasso.get().load(library.get(position).cover).into(coverImage)
    }

}


