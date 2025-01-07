package com.example.sushiv

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sushiv.Adapter.AllOptionAdapter
import com.example.sushiv.Model.Marriage_Data

class Marriage_Option : AppCompatActivity() {
    lateinit var marriageOption: Marriage_Option
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_marriage_option)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var myRecyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)




        val optionList = listOf(
            Marriage_Data("Invitation Card", "https://th.bing.com/th/id/OIP.qkk2V8Gbwevdr_Qg5rTDvgHaHa?w=802&h=802&rs=1&pid=ImgDetMain"),
            Marriage_Data("Wedding Food","https://th.bing.com/th/id/OIP.crYC5-RkmuCuVmwVmschFgHaE8?rs=1&pid=ImgDetMain"),
            Marriage_Data("Wedding Gifts","https://okcredit-blog-images-prod.storage.googleapis.com/2020/12/weddinggift.jpg"),
            Marriage_Data("Clothes ","https://th.bing.com/th/id/OIP.mKyBENCYYpPEXNzu4XQU0QHaJQ?rs=1&pid=ImgDetMain"),
            Marriage_Data("Cook", "https://st.focusedcollection.com/18590116/i/1800/focused_224833498-stock-photo-head-chef-teaching-his-team.jpg")
        )


        myRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AllOptionAdapter(this@Marriage_Option,optionList)
        myRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : AllOptionAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Marriage_Option, Option_Detail_Activity::class.java)
                intent.putExtra("NAME", optionList[position].name)

                startActivity(intent)
            }

        })
    }
}