package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.sushiv.Model.Vendor_Data
import com.example.sushiv.databinding.ActivityShopDetailTwoBinding
import com.google.firebase.database.FirebaseDatabase

class shopDetail_two : AppCompatActivity() {
    lateinit var binding: ActivityShopDetailTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShopDetailTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Glide.with(this)
            .asGif()
            .load(R.drawable.social) // Replace with your GIF resource
            .into(binding.socialGif)

        binding.nextButton.setOnClickListener {
            val name4 = intent.getStringExtra("NAME3")
            val email4 = intent.getStringExtra("EMAIL3")
            val password4 = intent.getStringExtra("PASSWORD3")
            val shopPhone = binding.editText.text.toString()
            val shopWebsite = binding.shopWebsite.text.toString()
            val instagram = binding.instagtam.text.toString()
            val shopName = intent.getStringExtra("SHOP_NAME1")
          val shopAddress2 =   intent.getStringExtra("SHOP_ADDRESS1")
            val nearestMetro2 = intent.getStringExtra("NEAREST_METRO1")
            val shopType2 = intent.getStringExtra("SHOP_TYPE1")
            val uid= intent.getStringExtra("UID")

            if (shopPhone.isEmpty()){
                Toast.makeText(this, "Fill the Detail",Toast.LENGTH_LONG).show()


            }else
            {
                val realtimeDb = FirebaseDatabase.getInstance().getReference(shopType2.toString())
                    .child(uid.toString())
                var vendorData = Vendor_Data(
                    name4.toString(),
                    email4.toString(),
                    password4.toString(),
                    shopAddress2.toString(),
                    shopType2.toString(),
                    shopName.toString(),
                   shopPhone.toString(),
                    shopWebsite.toString(),
                    instagram.toString(),
                    nearestMetro2.toString(),
                    uid.toString()



                )
                realtimeDb.setValue(vendorData).addOnSuccessListener {

                    Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Data Not Saved", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, shopDetail_three::class.java)

                intent.putExtra("SHOP_TYPE2", shopType2)
                intent.putExtra("UID", uid)

                startActivity(intent)

            }

            }


        }


    }


