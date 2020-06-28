package com.example.weatherapp.ui.fullimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fullscreen.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.ui.fullimages.adapter.ImagesAdapter


class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)


        /**
         * get images list and position for clicked item form intent
         */
        var list = intent.getParcelableArrayListExtra<SavedImageObject>("itemsData")
        var position = intent.getIntExtra("position", 0)

        //set images recycler view snapHelper
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(views_rv)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        views_rv.layoutManager = layoutManager

        views_rv.adapter = ImagesAdapter(list)
        views_rv.scrollToPosition(position)
        /**
         * get current scrolled image position from images list and display in counter textview
         */
        views_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findFirstCompletelyVisibleItemPosition() >= 0) {
                    position_tx.text = (layoutManager.findFirstCompletelyVisibleItemPosition() + 1).toString() + "/" + list.size.toString()
                }
            }
        })
        close.setOnClickListener { finish() }
    }

}
