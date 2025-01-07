package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sushiv.Adapter.AllOptionAdapter

import com.example.sushiv.Adapter.allShopAdapter
import com.example.sushiv.Model.allShopData
import com.example.sushiv.databinding.ActivityOptionDetailBinding

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Option_Detail_Activity : AppCompatActivity() {
    lateinit var binding : ActivityOptionDetailBinding
    lateinit var realtimeDB : DatabaseReference
    lateinit var adapter: allShopAdapter
    lateinit var shopList : ArrayList<allShopData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOptionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var shopCategory = intent.getStringExtra("NAME")


       binding.detailRecyclerView.layoutManager = LinearLayoutManager(this)
        shopList=arrayListOf()
        adapter= allShopAdapter(this,shopList)
        binding.detailRecyclerView.adapter= adapter




        val database = FirebaseDatabase.getInstance()
        val womenDressRef = database.getReference(shopCategory.toString())
      @Suppress("NotifyDataSetChanged")
        womenDressRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                shopList.clear()
                for (shopSnapshot in snapshot.children) {
                    val shop = shopSnapshot.getValue(allShopData::class.java)
                    shop?.let { shopList.add(shop) }
                    binding.detailRecyclerView.adapter?.notifyDataSetChanged()
                }

                binding.detailRecyclerView.adapter= allShopAdapter(this@Option_Detail_Activity,shopList)
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })



    }
}