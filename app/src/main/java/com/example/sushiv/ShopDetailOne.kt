package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.sushiv.Adapter.spinnerAdapter
import com.example.sushiv.databinding.ActivityShopDetailOneBinding
import com.google.firebase.auth.FirebaseAuth

class ShopDetailOne : AppCompatActivity() {
    lateinit var binding : ActivityShopDetailOneBinding
    lateinit var shopType : String
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShopDetailOneBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Glide.with(this)
            .asGif()
            .load(R.drawable.git_a) // Replace with your GIF resource
            .into(binding.gif)

        val spinner = binding.shopType
        val items = listOf("Select Category","Clothes ", "Invitation Card", "Honeymoon", "Wedding Card","Cook")
        val adapter = spinnerAdapter(this, items)


        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()


                val shopType = selectedItem.toString()
                binding.shopCategray.setText(shopType)


                Toast.makeText(this@ShopDetailOne, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }




        binding.nextButton.setOnClickListener {
            val name3 = intent.getStringExtra("NAME2")
            val email3 = intent.getStringExtra("EMAIL2")
            val password3 = intent.getStringExtra("PASSWORD2")
            auth= FirebaseAuth.getInstance()
            val uid = intent.getStringExtra("UID")
            val shopName = binding.editText.text.toString()
            val shopAddress = binding.editText3.text.toString()
            val nearestMetro = binding.editText4.text.toString()
            val shopType1 = binding.shopCategray.text.toString()

            if (shopName.isEmpty() || shopAddress.isEmpty()) {
                Toast.makeText(this, "Fill the Detail",Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, shopDetail_two::class.java)
                intent.putExtra("SHOP_NAME1", shopName)
                intent.putExtra("SHOP_ADDRESS1", shopAddress.toString())
                intent.putExtra("NEAREST_METRO1", nearestMetro.toString())
                intent.putExtra("SHOP_TYPE1", shopType1)
                intent.putExtra("NAME3", name3)
                intent.putExtra("EMAIL3", email3)
                intent.putExtra("PASSWORD3", password3)
                intent.putExtra("UID", uid)

                startActivity(intent)

            }


                }

            }


        }





