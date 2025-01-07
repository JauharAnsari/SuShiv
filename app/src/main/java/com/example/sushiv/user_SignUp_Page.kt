package com.example.sushiv

import android.app.Activity
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
import com.example.sushiv.Model.UserData
import com.example.sushiv.databinding.ActivityUserSignUpPageBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.firestore
import java.util.concurrent.TimeUnit

class user_SignUp_Page : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var db : FirebaseFirestore

    lateinit var binding : ActivityUserSignUpPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityUserSignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var  db = Firebase.firestore


binding.backLoginPage.setOnClickListener {
    startActivity(Intent(this,user_Login_Page::class.java))
}
        binding.button.setOnClickListener {
            val sName = binding.name.text.toString().trim()
            val sEmail = binding.email.text.toString().trim()
            val sPassword = binding.password.text.toString().trim()


            if (sName.isEmpty()||sEmail.isEmpty()||sPassword.isEmpty()) {
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()


            }else{
                val userData = hashMapOf(
                    "name" to sName,
                    "email" to sEmail,
                    "password" to sPassword
                )
                db.collection("UserAuth").document(sName).set(userData).addOnSuccessListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,user_Login_Page::class.java))


                }.addOnFailureListener {

                    Toast.makeText(this, "Data not inserted $", Toast.LENGTH_SHORT).show()

                }

            }

        }

    }



}