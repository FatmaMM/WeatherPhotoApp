package com.example.weatherapp.ui.fullimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.item_full_screen.*


class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_full_screen)


        /**
         * get images list and position for clicked item form intent
         */
//        var list = intent.getParcelableArrayListExtra<SavedImageObject>("itemsData")
//        var position = intent.getIntExtra("position", 0)


        /**
         * get current scrolled image position from images list and display in counter textview
         */

        close.setOnClickListener { finish() }
    }

}
