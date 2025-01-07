package com.example.sushiv.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudinary.android.uploadwidget.UploadWidget.startActivity
import com.example.sushiv.Model.allShopData
import com.example.sushiv.Option_Detail_Activity
import com.example.sushiv.R
import com.example.sushiv.detailOfShop
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class allShopAdapter(var context: Context,var shopList : List<allShopData>): RecyclerView.Adapter<allShopAdapter.ViewHolder>() {




    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var shopName = itemView.findViewById<TextView>(R.id.shopName2)
        var shopAddress = itemView.findViewById<TextView>(R.id.shopAddress2)
        var shopImage = itemView.findViewById<ShapeableImageView>(R.id.shopImage)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): allShopAdapter.ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_detail_card,parent,false)
        return ViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: allShopAdapter.ViewHolder, position: Int) {

        var currentItem = shopList[position]
        holder.shopName.text = currentItem.shopName
        holder.shopAddress.text = currentItem.shopAddress
        Picasso.get().load(currentItem.shopImage1).placeholder(R.drawable.error).into(holder.shopImage)
        val shopCatergry = currentItem.shopDescription
        val shopImageOne = currentItem.shopImage1
        val shopImageTwo = currentItem.shopImage2
        val shopImageThree = currentItem.shopImage3
        val shopImageFour = currentItem.shopImage4
        val uID = currentItem.toString
        var address = currentItem.shopAddress
        var name = currentItem.shopName
        var phone = currentItem.shopPhoneNumber
        var instagram = currentItem.shopInstagram
        var website = currentItem.shopWebsite
        var metro = currentItem.nearestMetro



    // Picasso.get().load(currentItem.shopImage1).into(holder.shopImage)
        holder.itemView.setOnClickListener {
            var intent = Intent(context,detailOfShop::class.java)
            intent.putExtra("UID",uID)
            intent.putExtra("SHOP_IMAGE_ONE",shopImageOne)
            intent.putExtra("SHOP_CATEGORY",shopCatergry)
            intent.putExtra("SHOP_IMAGE_TWO",shopImageTwo)
            intent.putExtra("SHOP_IMAGE_THREE",shopImageThree)
            intent.putExtra("SHOP_IMAGE_FOUR",shopImageFour)
            intent.putExtra("SHOP_NAME",name)
            intent.putExtra("SHOP_ADDRESS",address)
            intent.putExtra("SHOP_PHONE",phone)
            intent.putExtra("SHOP_INSTAGRAM",instagram)
            intent.putExtra("SHOP_WEBSITE",website)
            intent.putExtra("SHOP_METRO",metro)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return shopList.size

    }




}