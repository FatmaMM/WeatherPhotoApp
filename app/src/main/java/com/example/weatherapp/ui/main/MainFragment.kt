package com.example.weatherapp.ui.main

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.core.content.ContextCompat
import android.util.Log
import android.widget.Toast
import androidx.camera.core.*
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.weatherapp.BR
import com.example.weatherapp.databinding.MainFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.history.HistoryFragment
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.io.FileNotFoundException
import java.io.InputStream
import androidx.lifecycle.Observer
import com.example.weatherapp.data.model.SavedImageObject

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>(), LocationListener {
    private lateinit var photoFile: File
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var camera: Camera? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    lateinit var savedUri: Uri
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private var isGPSEnable = false
    private var isNetworkEnable = false
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var locationManager: LocationManager
    private var location: Location? = null
    private var imageSaved = SavedImageObject()

    companion object {
        fun newInstance() = MainFragment()
        private const val MY_PERMISSIONS_REQUEST_CAMERA = 12
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.main_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cameraProviderFuture = ProcessCameraProvider.getInstance(context!!)

        if (allPermissionsGranted()) {
            startCamera()
            getLocation()
        } else {
            requestPermissions(
                REQUIRED_PERMISSIONS, MY_PERMISSIONS_REQUEST_CAMERA
            )
        }

        capture.setOnClickListener { takePhoto() }
        share.setOnClickListener {
            shareImage()
        }
        save.setOnClickListener {
            mViewModel.save(imageSaved)
        }
        history.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commitNow()
        }

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()

        mViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT)
                .show()
        })

        mViewModel.responseModel.observe(viewLifecycleOwner, Observer {
            images.visibility = View.VISIBLE
            imageSaved.temp = it.current.temp
            imageSaved.desc = it.current.weather[0].description
            imageSaved.icon = it.current.weather[0].icon
            imageSaved.imagepath = savedUri.toString()
        })
        mViewModel.locationName.observe(viewLifecycleOwner, Observer {
            imageSaved.address = it
        })

        mViewModel.imageSaved.observe(viewLifecycleOwner, Observer {
            if (it) {
                save.visibility = View.GONE
                saved.visibility = View.VISIBLE
                Toast.makeText(context, getString(R.string.savedMessage), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (allPermissionsGranted()) {
                startCamera()
                getLocation()
            } else {
                Toast.makeText(
                    context!!,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onLocationChanged(p0: Location?) {
        this.location = p0
        if (location != null) {
            latitude = location!!.latitude
            longitude = location!!.longitude
        }

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    private fun shareImage() {
    }


    private fun startCamera() {
        try {
            imageCapture = ImageCapture.Builder()
                .build()
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context!!)

            cameraProviderFuture.addListener(Runnable {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                // Preview
                preview = Preview.Builder()
                    .build()

                // Select back camera
                val cameraSelector =
                    CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                        .build()

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    camera = cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture
                    )
                    preview?.setSurfaceProvider(viewFinder.createSurfaceProvider())


                } catch (exc: Exception) {
                    Log.e(TAG, "Use case binding failed", exc)
                }

            }, ContextCompat.getMainExecutor(context))
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "camera error", e)
        }


    }

    private fun takePhoto() {
        photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )
        savedUri = Uri.fromFile(photoFile)
        Log.e("saved:", savedUri.toString())
        viewFinder.visibility = View.GONE
        imageView.visibility = View.VISIBLE
        capture.visibility = View.GONE
        saveIntoFolder()

        if (latitude != 0.0 && longitude != 0.0)
            mViewModel.getCurrentWeatherData(latitude, longitude, "")

    }

    private fun getLocation() {
        try {
            locationManager =
                context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnable && !isNetworkEnable) {
            } else {
                if (isNetworkEnable) {
                    location = null
                    if (ContextCompat.checkSelfPermission(
                            context!!,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            100,
                            0f,
                            this
                        )
                    }
                    if (locationManager != null) {
                        location =
                            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude

                        }
                    }
                }

                if (isGPSEnable) {
                    location = null
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        100,
                        0f,
                        this
                    )
                    if (locationManager != null) {
                        location =
                            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                    }
                }
            }
        } catch (e: Exception) {

        }
    }

    fun decodeUriToBitmap(sendUri: Uri): Bitmap? {
        var getBitmap: Bitmap? = null
        try {
            var image_stream: InputStream
            try {
                image_stream =
                    context!!.contentResolver.openInputStream(Uri.fromFile(File(sendUri.path!!)))!!
                getBitmap = BitmapFactory.decodeStream(image_stream)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return getBitmap
    }

    private fun saveIntoFolder() {
        val imageCapture = imageCapture ?: return
        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        // Setup image capture listener which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    imageView.setImageBitmap(decodeUriToBitmap(savedUri))
                }
            })
    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            context!!, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun getOutputDirectory(): File {
        val mediaDir = activity!!.externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else activity!!.filesDir
    }

}
