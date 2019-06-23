package com.tstipanic.factorynews_kotlin.map_screen

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.common.EXTRA_MESSAGING_DATA1
import com.tstipanic.factorynews_kotlin.common.EXTRA_MESSAGING_DATA2
import com.tstipanic.factorynews_kotlin.common.EXTRA_MESSAGING_TITLE
import kotlinx.android.synthetic.main.activity_maps.*

const val FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
const val COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION
const val LOCATION_PERMISSION_REQUEST_CODE = 1234
const val DEFAULT_ZOOM = 16f

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationPermissionGranted = false

    companion object {
        var active = false
    }

    override fun onResume() {
        super.onResume()
        active = true
    }

    override fun onPause() {
        super.onPause()
        active = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setSupportActionBar(mapsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initMap()
        getLocationPermission()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.home_button -> findHomeLocation()
        R.id.work_place -> findWorkplaceLocation()
        R.id.current_location -> {getDeviceLocation(); Log.d("ItemSelected", "current Location"); true }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun findWorkplaceLocation(): Boolean {
        val zoomLevel = 18f
        val workplaceLocation = LatLng(45.560872, 18.680627)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(workplaceLocation, zoomLevel))
        val marker = mMap.addMarker(MarkerOptions().title("Blue Factory").position(workplaceLocation))
        return true
    }

    private fun getLocationFromCoordinates(lat: Double, lng: Double, title: String) {
        val latLng = LatLng(lat, lng)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM))
        val marker = mMap.addMarker(MarkerOptions().position(latLng).title(title))
    }

    private fun findHomeLocation(): Boolean {
        val zoomLevel = 14f
        val homeTownLocation = LatLng(45.603965, 18.743852)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeTownLocation, zoomLevel))
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (locationPermissionGranted) {
            getDeviceLocation()

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = false
        }

        if (intent.extras != null) {

            val lat = intent.getStringExtra(EXTRA_MESSAGING_DATA1).toDouble()
            val lng = intent.getStringExtra(EXTRA_MESSAGING_DATA2).toDouble()
            val title = intent.getStringExtra(EXTRA_MESSAGING_TITLE)
            getLocationFromCoordinates(lat, lng, title)
        }
    }

    private fun getLocationPermission() {
        val permissions = arrayListOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    COURSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationPermissionGranted = true
            } else {
                ActivityCompat.requestPermissions(this, permissions.toTypedArray(), LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun moveCamera(latLng: LatLng, zoom: Float) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    private fun getDeviceLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            if (locationPermissionGranted) {
                val location = fusedLocationProviderClient.lastLocation
                location.addOnCompleteListener(object : OnCompleteListener<Location> {
                    override fun onComplete(p0: Task<Location>) {
                        if (p0.isSuccessful) {
                            val currentLocation = p0.result
                            moveCamera(LatLng(currentLocation!!.latitude, currentLocation.longitude), DEFAULT_ZOOM)
                        } else {
                            Toast.makeText(this@MapsActivity, "Unable to get current location.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }
        } catch (e: SecurityException) {
            Log.e("getDeviceLocation", "Security Exception!!")
            e.printStackTrace()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        locationPermissionGranted = false
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    try {
                        for (i in 0..grantResults.size) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                locationPermissionGranted = false
                                return
                            }
                        }
                        locationPermissionGranted = true
                        initMap()
                    }catch (e: ArrayIndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}






