package com.example.sushiv

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import java.util.Locale


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)



    }

    override fun onMapReady(map: GoogleMap) {
       val mapAddress = intent.getStringExtra("ADDRESS").toString()
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(mapAddress, 1)
        var address = addresses?.get(0)
        val latitude = address?.latitude
        val longitude = address?.longitude

        val latLng = com.google.android.gms.maps.model.LatLng(latitude!!, longitude!!)
        map.addMarker(com.google.android.gms.maps.model.MarkerOptions().position(latLng).title(mapAddress))
        map.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 17f))





    }
}



