package com.example.sushiv.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sushiv.Model.Marriage_Data
import com.example.sushiv.Option_Detail_Activity
import com.example.sushiv.R
import com.squareup.picasso.Picasso



public class AllOptionAdapter(var context: Context, var optionList: List<Marriage_Data>): RecyclerView.Adapter<AllOptionAdapter.ViewHolder>() {
    private lateinit var myListner :onItemClickListener
    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        myListner = listener
    }
   public class ViewHolder(var itemView:View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        var Title = itemView.findViewById<TextView>(R.id.title)
        var Image = itemView.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.cardImage)

       init {
               itemView.setOnClickListener {
                   listener.onItemClick(adapterPosition)

               }
       }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOptionAdapter.ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.wedding_item_card,parent,false)
        return ViewHolder(itemView,myListner)
    }

    public override fun onBindViewHolder(holder: AllOptionAdapter.ViewHolder, position: Int) {
        var currentItem = optionList[position]
        holder.Title.text = currentItem.name
       Picasso.get().load(currentItem.imageUrl).into(holder.Image)



    }

    override fun getItemCount(): Int {
        return optionList.size
    }
}