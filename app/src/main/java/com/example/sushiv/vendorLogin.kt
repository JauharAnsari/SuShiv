package com.example.sushiv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sushiv.databinding.ActivityVendorLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class vendorLogin : AppCompatActivity() {
    lateinit var binding: ActivityVendorLoginBinding
    lateinit var auth: FirebaseAuth
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val Auth = FirebaseAuth.getInstance()
    private val Database = FirebaseDatabase.getInstance()
    lateinit var database: FirebaseDatabase

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendorLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        binding.registerPage.setOnClickListener {
            startActivity(Intent(this, vendorSignUp::class.java))
            finish()
        }
        binding.loginButton.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all the Details", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    var uid = auth.currentUser?.uid.toString()


                    val name2 = intent.getStringExtra("NAME1")
                    val email2 = intent.getStringExtra("EMAIL1")
                    val password2 = intent.getStringExtra("PASSWORD1")

                    if (task.isSuccessful) {

                        var verification = auth.currentUser?.isEmailVerified
                        checkUserInAuthVender(uid)
                        if (verification == true) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            var i = Intent(this, ShopDetailOne::class.java)
                            val uid = auth.currentUser!!.uid
                            i.putExtra("UID", uid)
                            i.putExtra("NAME2", name2)
                            i.putExtra("EMAIL2", email2)
                            i.putExtra("PASSWORD2", password2)
                            startActivity(i)
                            finish()

                        }
                    }
                }
            }
        }
    }
    private fun checkUserInAuthVender(uid: String) {
        scope.launch {
            withContext(Dispatchers.IO){

                Database.reference.child("AuthVender").child(uid)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            runOnUiThread {
                                if (snapshot.exists()) {
                                val intent = Intent(this@vendorLogin, MyShopDetail::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(
                                this@vendorLogin, "Error: ${error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    }
            }

        }
}

