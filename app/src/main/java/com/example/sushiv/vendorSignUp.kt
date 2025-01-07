package com.example.sushiv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sushiv.databinding.ActivityVendorSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class vendorSignUp : AppCompatActivity() {
    lateinit var binding : ActivityVendorSignUpBinding
    lateinit var auth : FirebaseAuth
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityVendorSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
binding.returnLogin.setOnClickListener {
    startActivity(Intent(this,vendorLogin::class.java))
    finish()
}

        auth= FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val name1 = binding.editTextText2.text.toString()
            val email1= binding.editTextTextEmailAddress2.text.toString()
            val password1 = binding.editTextTextPassword2.text.toString()
            if(name1.isEmpty() || email1.isEmpty() || password1.isEmpty()){
                Toast.makeText(this,"Please enter all the Details",Toast.LENGTH_SHORT).show()

            }else{
                auth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener {
                    auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                        Toast.makeText(this, "Verification Email Sent", Toast.LENGTH_SHORT).show()

                      var i = Intent(this,vendorLogin::class.java)
                        i.putExtra("NAME1",name1)
                        i.putExtra("EMAIL1",email1)
                        i.putExtra("PASSWORD1",password1)
                        startActivity(i)
                        finish()

                    }

                    }
            }
        }

    }
}