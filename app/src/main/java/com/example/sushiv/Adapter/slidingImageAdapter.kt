package com.example.sushiv.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.sushiv.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class slidingImageAdapter(var imageList : ArrayList<String>,var viewPager:ViewPager2):RecyclerView.Adapter<slidingImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ShapeableImageView>(R.id.slidingImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.sliding_imageview,parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
       return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(imageList[position]).into(holder.imageView)

        if (position==imageList.size-1){
            viewPager.post(runnable)


        }
    }
    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()

    }

}