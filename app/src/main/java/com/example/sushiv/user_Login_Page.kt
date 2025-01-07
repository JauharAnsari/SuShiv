package com.example.sushiv

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sushiv.Model.sessionManager
import com.example.sushiv.databinding.ActivityUserLoginPageBinding
import com.google.firebase.Firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore


class user_Login_Page : AppCompatActivity() {
    lateinit var databaseRefrence : DatabaseReference
    lateinit var binding : ActivityUserLoginPageBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserLoginPageBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //var db = Firebase.firestore
       val sessionManager = sessionManager(this)

        // Check if user is already logged in
        if (sessionManager.isLoggedIn()) {
            // User is already logged in, go directly to main activity
            startActivity(Intent(this, Marriage_Option::class.java))
            finish()
        }

        binding.registerPage.setOnClickListener {
            startActivity(Intent(this,user_SignUp_Page::class.java))
        }


        binding.loginButton.setOnClickListener {
            val yourEmail = binding.email.text.toString().trim()
            val yourPassword = binding.password.text.toString().trim()
            if (yourEmail.isEmpty() && yourPassword.isEmpty()) {
               Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()


            }else{

                val db = Firebase.firestore
                db.collection("UserAuth")
                    .whereEqualTo("email", yourEmail)
                    .whereEqualTo("password", yourPassword)
                    .get()
                    .addOnSuccessListener {it->
                    if (!it.isEmpty) {
                        sessionManager.createLoginSession(yourEmail)
                      startActivity(Intent(this,Marriage_Option::class.java))

                    }

                }


            }

        }
    }


}

