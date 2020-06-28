package com.example.weatherapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.BR

import com.example.weatherapp.R
import com.example.weatherapp.databinding.HistoryFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment
import com.example.weatherapp.ui.base.MainActivity
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
    }

}
