package com.example.weatherapp.ui.fullimages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.databinding.ItemFullScreenBinding

class ImagesAdapter(var list: ArrayList<SavedImageObject>) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemBinded = DataBindingUtil.inflate<ItemFullScreenBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_full_screen,
            parent,
            false
        )
        return ViewHolder(itemBinded)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = list[position]
        holder.onBind(item)
    }

    class ViewHolder(var itemBinding: ItemFullScreenBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(responseItemData: SavedImageObject) {
            itemBinding.currentitem = responseItemData
        }
    }
}