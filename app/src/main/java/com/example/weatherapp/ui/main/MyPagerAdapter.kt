package com.example.weatherapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weatherapp.R
import com.example.weatherapp.ui.history.HistoryFragment

class MyPagerAdapter constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // Returns total number of pages
    override fun getCount(): Int {
        return 2
    }

    // Returns the fragment to display for that page

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainFragment.newInstance()
            1 -> return HistoryFragment.newInstance()
        }
        return MainFragment.newInstance()
    }

}