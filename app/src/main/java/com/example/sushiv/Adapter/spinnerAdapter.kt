package com.example.sushiv.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class spinnerAdapter(context: Context, private val items: List<String>): ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val textView: TextView = view.findViewById(android.R.id.text1)

        // Set the item text
        textView.text = items[position]

        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getDropDownView(position, convertView, parent)
    }
}