package com.example.weatherapp.ui.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.weatherapp.BR

import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.databinding.HistoryFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.base.MainActivity
import com.example.weatherapp.ui.history.adapter.ItemAdapter
import com.example.weatherapp.ui.history.adapter.OnCharacterListsItemClicked
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : BaseFragment<HistoryFragmentBinding, HistoryViewModel>() {
    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.history_fragment
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        back.setOnClickListener { (activity!! as MainActivity).onBackPressed() }

        //update ui with images list
        mViewModel.images.observe(viewLifecycleOwner, Observer {
            var adapter = ItemAdapter(it)
            rv_images.adapter = adapter
            adapter.onItemClick = object : OnCharacterListsItemClicked {
                override fun onItemClick(item: SavedImageObject, pos: Int) {
//                    openImagesActivity(items, pos)

                }


            }
        })
    }

}
