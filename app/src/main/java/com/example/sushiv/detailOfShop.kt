package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.sushiv.Adapter.slidingImageAdapter
import com.example.sushiv.databinding.ActivityDetailOfShopBinding
import kotlin.math.abs

class detailOfShop : AppCompatActivity() {
    lateinit var binding: ActivityDetailOfShopBinding
    private lateinit var handler : Handler
    private lateinit var imageList : ArrayList<String>
    private lateinit var myAdapter: slidingImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailOfShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var shopName = binding.textView22
        var shopAddress = binding.textView24
        var shopPhoneNumber = binding.textView29
        var shopInstagram = binding.instagram
        var shopWebsite =binding.textView30
        var shopMetro = binding.metro
        var buttonMap = binding.mapButton




        val uID = intent.getStringExtra("UID")

        val shopCategory = intent.getStringExtra("SHOP_CATEGORY")
        val shopImageOne = intent.getStringExtra("SHOP_IMAGE_ONE")
        val shopImageTwo = intent.getStringExtra("SHOP_IMAGE_TWO")
        val shopImageThree = intent.getStringExtra("SHOP_IMAGE_THREE")
        val shopImageFour = intent.getStringExtra("SHOP_IMAGE_FOUR")
        val shopName1 = intent.getStringExtra("SHOP_NAME")
        val shopAddress1 = intent.getStringExtra("SHOP_ADDRESS")
        val shopPhoneNumber1 = intent.getStringExtra("SHOP_PHONE")
        val shopInstagram1 = intent.getStringExtra("SHOP_INSTAGRAM")
        val shopWebsite1 = intent.getStringExtra("SHOP_WEBSITE")
        val shopMetro1 = intent.getStringExtra("SHOP_METRO")

        shopName.text = shopName1
        shopAddress.text = shopAddress1
        shopPhoneNumber.text = shopPhoneNumber1
        shopInstagram.text = shopInstagram1
        shopWebsite.text = shopWebsite1
        shopMetro.text = shopMetro1

        buttonMap.setOnClickListener {
         val i = Intent(this,MapActivity::class.java)
         i.putExtra("ADDRESS",shopAddress1)
            startActivity(i)

        }


        handler = Handler(Looper.myLooper()!!)
        imageList=ArrayList()

        imageList.add(shopImageOne.toString())
        imageList.add(shopImageTwo.toString())
        imageList.add(shopImageThree.toString())
       imageList.add(shopImageFour.toString())
        myAdapter = slidingImageAdapter(imageList,binding.viewPager)
        binding.viewPager.adapter=myAdapter
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.clipToPadding=false
        binding.viewPager.clipChildren=false
        binding.viewPager.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()
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

    private val runnable = Runnable {
        binding.viewPager.currentItem=binding.viewPager.currentItem+1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f

        }
        binding.viewPager.setPageTransformer(transformer)

    }
}