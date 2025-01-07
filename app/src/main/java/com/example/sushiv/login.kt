package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sushiv.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var userLogin = findViewById<Button>(R.id.user)
        var userCardView = findViewById<CardView>(R.id.cardView)
        var registerTextView = findViewById<TextView>(R.id.textView)

        binding.user.setOnClickListener {
            startActivity(Intent(this, user_Login_Page::class.java))
        }
        binding.vendorButton.setOnClickListener {
            startActivity(Intent(this, vendorSignUp::class.java))


        }


    }
}