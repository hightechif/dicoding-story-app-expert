package com.fadhil.storyappexpert.maps.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fadhil.storyappexpert.core.data.ProcessResult
import com.fadhil.storyappexpert.domain.model.Story
import com.fadhil.storyappexpert.maps.databinding.ActivityStoryMapsBinding

@dagger.hilt.android.AndroidEntryPoint
class StoryMapsActivity : androidx.appcompat.app.AppCompatActivity(),
    com.google.android.gms.maps.OnMapReadyCallback {

    private lateinit var mMap: com.google.android.gms.maps.GoogleMap
    private lateinit var binding: ActivityStoryMapsBinding
    private val viewModel: StoryMapsViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoryMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupListener()
        setupObserver()
        initData()
    }

    private fun setupView() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment =
            supportFragmentManager.findFragmentById(com.fadhil.storyappexpert.maps.R.id.map) as com.google.android.gms.maps.SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupListener() {

    }

    private fun setupObserver() {

    }

    private fun initData() {

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: com.google.android.gms.maps.GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        getMyLocation()
        getAllStories()
    }

    private fun drawMarker(
        lat: Double,
        long: Double,
        title: String,
        snippet: String,
        zoom: Float? = null
    ) {
        val latLng = com.google.android.gms.maps.model.LatLng(lat, long)
        mMap.addMarker(
            com.google.android.gms.maps.model.MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
        )
        if (zoom != null) {
            mMap.animateCamera(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                    latLng,
                    zoom
                )
            )
        } else {
            mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(latLng))
        }
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun getAllStories() {
        viewModel.setPage(0)
        viewModel.setSize(10)
        viewModel.setLocation(1)
        viewModel.getAllStories(true)
            .observe(this) {
                ProcessResult(
                    it,
                    object :
                        com.fadhil.storyappexpert.core.data.ProcessResultDelegate<List<Story>?> {
                        override fun loading() {
                        }

                        override fun error(code: String?, message: String?) {
                        }

                        override fun unAuthorize(message: String?) {
                        }

                        override fun success(data: List<Story>?) {
                            if (data?.isNotEmpty() == true) {
                                data.forEach { story ->
                                    if (story.lat != null && story.lon != null)
                                        drawMarker(
                                            story.lat!!,
                                            story.lon!!,
                                            story.name,
                                            story.description
                                        )
                                }
                            }
                        }

                    })
            }
    }

    companion object {
        fun open(
            originActivity: androidx.fragment.app.FragmentActivity,
            resultLauncher: androidx.activity.result.ActivityResultLauncher<Intent>? = null
        ) {
            val intent = Intent(originActivity, StoryMapsActivity::class.java)
            if (resultLauncher != null) {
                resultLauncher.launch(intent)
            } else {
                ActivityCompat.startActivity(originActivity, intent, null)
            }
        }
    }
}