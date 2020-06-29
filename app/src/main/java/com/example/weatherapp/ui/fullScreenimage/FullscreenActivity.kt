package com.example.weatherapp.ui.fullScreenimage

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.databinding.ItemFullScreenBinding
import com.example.weatherapp.ui.base.BaseActivity
import com.example.weatherapp.ui.main.MainFragment
import kotlinx.android.synthetic.main.item_full_screen.*
import kotlinx.android.synthetic.main.item_full_screen.share
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.share_image_dialog.*
import java.io.File


class FullscreenActivity : BaseActivity<ItemFullScreenBinding, FullScreenViewModel>() {
    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.item_full_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * get image and position for clicked item form intent
         */
        var item = intent.getParcelableExtra<SavedImageObject>("itemsData")
        //set viewDataBinding variables
        viewDataBinding!!.currentitem = item

        close.setOnClickListener { finish() }
        share.setOnClickListener {
            if (null == item!!.imagepath) {
                Toast.makeText(
                    this!!,
                    getString(R.string.cap_image_first),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var dialog = Dialog(this!!)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.share_image_dialog)
                dialog.facebook.setOnClickListener {
                    shareImage(
                        getString(R.string.facebook),
                        Uri.parse((item!!.imagepath!!))
                    )
                }
                dialog.twitter.setOnClickListener {
                    shareImage(getString(R.string.twitter), Uri.parse(item!!.imagepath!!))
                }
                dialog.show()
            }
        }
    }

    private fun shareImage(app: String, savedUri: Uri) {
        if (savedUri!=null){
            Uri.fromFile(File(savedUri.path))
        }
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        shareIntent.type = "image/*"
        if (app == getString(R.string.facebook) && isAppInstalled("com.facebook.katana")) {
            shareIntent.setPackage("com.facebook.katana")
        }
        if (app == getString(R.string.twitter) && isAppInstalled("com.twitter.android")) {
            shareIntent.setPackage("com.twitter.android")
        }
        shareIntent.putExtra(Intent.EXTRA_STREAM, savedUri)
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this!!, getString(R.string.notFoundApp), Toast.LENGTH_LONG).show()
            Log.e(MainFragment.TAG, "ActivityNotFoundException: ", ex)
        }
    }

    private fun isAppInstalled(app: String): Boolean {
        return try {
            this.packageManager.getApplicationInfo(app, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}
