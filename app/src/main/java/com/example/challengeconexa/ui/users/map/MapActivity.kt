package com.example.challengeconexa.ui.users.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeconexa.R
import com.example.challengeconexa.data.model.User
import com.example.challengeconexa.databinding.ActivityMapBinding
import com.example.challengeconexa.utils.setupToolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding by lazy { ActivityMapBinding.inflate(layoutInflater) }
    private lateinit var user: User

    companion object {
        private const val ARG_USER = "ARG_USER"
        fun getIntent(context: Context, user: User) =
            Intent(context, MapActivity::class.java)
                .putExtra(ARG_USER, Gson().toJson(user))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar(binding.containerToolbar.toolbar, getString(R.string.user_location))

        intent.getStringExtra(ARG_USER).let { user = Gson().fromJson(it, User::class.java) }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLong = LatLng(
            user.address?.geo?.lat?.toDouble() ?: 0.0,
            user.address?.geo?.lng?.toDouble() ?: 0.0
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(latLong)
                .title(user.firstname)
                .snippet(user.email)
        )?.showInfoWindow()
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLong,
                12f
            )
        )
    }
}