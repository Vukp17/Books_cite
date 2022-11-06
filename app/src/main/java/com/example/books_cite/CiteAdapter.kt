package com.example.books_cite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CiteAdapter:RecyclerView.Adapter<CiteAdapter.CustomViewHolder>() {

    val citeTitles = listOf<String>("Miami by Joan Didion - Speaking of California","The Jew of New York by Ben Katchor - An overstuffed, Pynchonesque","City Primeval by Elmore Leonard - Detroit! As you can imagine, picking" )
    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val textView: TextView = view.findViewById(R.id.textViewCite)

        fun bind(word: String) {
            textView.text = word
        }
    }
    override fun getItemCount(): Int {
        return citeTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layourInflater= LayoutInflater.from(parent?.context)
        val citeForRow= layourInflater.inflate(R.layout.cite_row,parent,false)
        return CustomViewHolder(citeForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val citeTitle = citeTitles.get(position)
        holder?.view?.findViewById<TextView>(R.id.textViewCite)?.text = citeTitle
    }
}