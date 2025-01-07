package com.example.sushiv

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.sushiv.Adapter.slidingImageAdapter
import com.example.sushiv.Model.UserData
import com.example.sushiv.databinding.ActivityMyShopDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlin.math.abs

class MyShopDetail : AppCompatActivity() {
    lateinit var binding : ActivityMyShopDetailBinding
    lateinit var auth : FirebaseAuth
    private lateinit var handler : Handler
    private lateinit var imageList : ArrayList<String>
    private lateinit var myAdapter: slidingImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()

      val shopName = binding.textView22
        val shopAddress = binding.textView24
        val shopPhoneNumber = binding.textView29
        val Instagram = binding.instagram
        val Website = binding.textView30
        val Metro = binding.metro

        var uID = auth.currentUser?.uid

        val database1 = FirebaseDatabase.getInstance()
        val userDetail1 = database1.getReference("AuthVender")

        userDetail1.child(uID.toString()).get().addOnSuccessListener {
            if (it.exists()) {
                val shopCategory = it.child("shopType").value.toString()

                val database = FirebaseDatabase.getInstance()
                val userDetail = database.getReference(shopCategory)
                userDetail.child(uID.toString()).get().addOnSuccessListener {
                    if (it.exists()) {

                        val metro = it.child("nearestMetro").value.toString()
                        Metro.text = metro
                        val instagram = it.child("shopInstagram").value.toString()
                        Instagram.text = instagram
                        val website = it.child("shopWebsite").value.toString()
                        Website.text = website
                        val shopName1 = it.child("shopName").value.toString()
                        shopName.text = shopName1
                        val shopAddress1 = it.child("shopAddress").value.toString()
                        shopAddress.text = shopAddress1
                        val shopPhone = it.child("shopPhoneNumber").value.toString()
                        shopPhoneNumber.text = shopPhone
                        val shopImageOne = it.child("shopImage1").value.toString()
                        val shopImageTwo = it.child("shopImage2").value.toString()
                        val shopImageThree = it.child("shopImage3").value.toString()
                        val shopImageFour = it.child("shopImage4").value.toString()

                        imageList.add(shopImageOne)
                        imageList.add(shopImageTwo)
                        imageList.add(shopImageThree)
                        imageList.add(shopImageFour)

                        myAdapter = slidingImageAdapter(imageList, binding.viewPager)
                        binding.viewPager.adapter = myAdapter
                        binding.viewPager.offscreenPageLimit = 3
                        binding.viewPager.clipToPadding = false
                        binding.viewPager.clipChildren = false
                        binding.viewPager.getChildAt(0).overScrollMode =
                            RecyclerView.OVER_SCROLL_NEVER


                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }
        handler = Handler(Looper.myLooper()!!)
        imageList=ArrayList()
            setUpTransformer()





     //code for Sliding Image View

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }

        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }
        val runnable = Runnable {
        binding.viewPager.currentItem=binding.viewPager.currentItem+1
    }

     fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f

        }
        binding.viewPager.setPageTransformer(transformer)

    }
}