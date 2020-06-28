package com.example.weatherapp.ui.fullimages
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.databinding.ItemFullScreenBinding
import com.example.weatherapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.item_full_screen.*


class FullscreenActivity : BaseActivity<ItemFullScreenBinding,FullScreenViewModel>() {
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


        /**
         * get current scrolled image position from images list and display in counter textview
         */

        close.setOnClickListener { finish() }
    }


}
