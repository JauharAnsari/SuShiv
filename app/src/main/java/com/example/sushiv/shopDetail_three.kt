package com.example.sushiv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.sushiv.Model.AuthVender
import com.example.sushiv.Model.Vendor_Data
import com.example.sushiv.Model.vendorData
import com.example.sushiv.databinding.ActivityShopDetailThreeBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.io.File
import java.util.UUID

class shopDetail_three : AppCompatActivity() {
    lateinit var binding: ActivityShopDetailThreeBinding
    private var imageUri: Uri? = null
    lateinit var realtimeDb: DatabaseReference
    private var selectedImageIndex = 0
    var imageIndex = 1
    val array = arrayOf("imageView1", "imageView2", "imageView3", "imageView4")

    private val imageUris = arrayOfNulls<Uri>(4)
    private val uploadedImageUrls = mutableListOf<String>()


    companion object {
        private const val PICK_IMAGE_REQUEST = 0
    }

    private val cloudinary = Cloudinary(
        mapOf(
            "cloud_name" to "diewweq7p",
            "api_key" to "957192226495787",
            "api_secret" to "ypMoLF6X_ZWDwCAIhy7t-54n308"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailThreeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val shopImageOne = binding.imageView1
        val shopImageTwo = binding.imageView3
        val shopImageThree = binding.imageView2
        val shopImageFour = binding.imageView4
        val shopImageFive = binding.shapeableImageView
        val shopImageSix = binding.shapeableImageView2

binding.finishButton.setOnClickListener {
    if (shopImageOne==null && shopImageTwo==null){
        Toast.makeText(this, "Select Atleast Two Images", Toast.LENGTH_SHORT).show()
    }else{
        addUserUid()
        val intent = Intent(this, MyShopDetail::class.java)
        startActivity(intent)
    }
}


        val galleryImage2 = registerForActivityResult( //imageView 2
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri->
                shopImageTwo.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(uri).into(shopImageTwo)
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }

        )

        shopImageTwo.setOnClickListener {

            galleryImage2.launch("image/*")
            shopImageTwo.isEnabled=false

        }/// imaggeView 2 end

        val galleryImage3 = registerForActivityResult(    // imageView 3
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri->
                shopImageThree.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(uri).into(shopImageThree)
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }

        )
        shopImageThree.setOnClickListener {

            galleryImage3.launch("image/*")
            shopImageThree.isEnabled=false

        }// imageView 3 end

        val galleryImage4 = registerForActivityResult( /// imageView 4
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri->
                shopImageFour.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(uri).into(shopImageFour)
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }

        )
        shopImageFour.setOnClickListener {
            galleryImage4.launch("image/*")
            shopImageFour.isEnabled=false
        }// imageView 4 end

        val galleryImage5 = registerForActivityResult( /// imageView 5
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri->
                shopImageFive.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(uri).into(shopImageFive)
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }

        )
        shopImageFive.setOnClickListener {
            galleryImage5.launch("image/*")
            shopImageFive.isEnabled=false
        }


        val galleryImage6 = registerForActivityResult( // imageView 6
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri->
                shopImageSix.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(uri).into(shopImageSix)
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }

        )
        shopImageSix.setOnClickListener {
            galleryImage6.launch("image/*")
            shopImageSix.isEnabled=false
        }// imageView 6 end


shopImageOne.setOnClickListener {
    selectImage()
    shopImageOne.isEnabled = false
}

    }

    private fun selectImage() {

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            binding.imageView1.scaleType=ImageView.ScaleType.CENTER_CROP
            Picasso.get().load(imageUri).into(binding.imageView1)

            imageUri.let { uri->
                val filePath = getRealPathFromURI(uri!!)
                uploadImagesToCloudinary(filePath!!)
            }



        }
    }

    private fun uploadImagesToCloudinary(filePath: String)  {
        val file = File(filePath)
       Thread{
           try {
               val uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap())
               val imageUrl = uploadResult["secure_url"].toString()
               runOnUiThread {
                   saveUrlsToFirebase(imageUrl)
               }
           } catch (e: Exception) {
               e.printStackTrace()
               runOnUiThread{
                   Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()
               }
           }
       }.start()

        }




    @SuppressLint("SuspiciousIndentation")
    private fun saveUrlsToFirebase(imageUrls:String) {
        val Shop_Type = intent.getStringExtra("SHOP_TYPE2")
        val uid = intent.getStringExtra("UID")
        val database = FirebaseDatabase.getInstance()
        val vendorShopRef = database.getReference(Shop_Type.toString()).child(uid.toString())
        // Save URLs in Firebase under a single node

        val shopUpdates = hashMapOf<String, Any>(
            "shopImage$imageIndex" to imageUrls // Add the ShopImage URL
        )
          imageIndex++
        vendorShopRef.updateChildren(shopUpdates).addOnSuccessListener {
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getRealPathFromURI(contentUri: Uri): String? {
        var filePath: String? = null
        val cursor = contentResolver.query(contentUri, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(idx)
            cursor.close()
        }
        return filePath
    }

    fun addUserUid(){
         val database = FirebaseDatabase.getInstance()
      val auth = FirebaseAuth.getInstance()
        val authVenderRef = database.reference.child("AuthVender")
        val Shop_Type = intent.getStringExtra("SHOP_TYPE2")
        var UID = auth.currentUser?.uid
        var vendorData = AuthVender(UID,Shop_Type)

        authVenderRef.child(UID.toString()).setValue(vendorData)
    }



}


