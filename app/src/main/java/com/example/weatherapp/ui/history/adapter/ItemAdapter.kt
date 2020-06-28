package com.example.weatherapp.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SavedImageObject
import com.example.weatherapp.databinding.ItemImageBinding

class ItemAdapter(var list: ArrayList<SavedImageObject>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    lateinit var onItemClick: OnCharacterListsItemClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemBinded = DataBindingUtil.inflate<ItemImageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_image,
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
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(item, position)
        }
    }

    class ViewHolder(var itemBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(responseItemData: SavedImageObject) {
            if (responseItemData.imagepath != null) {
                itemBinding.imageUrl = responseItemData.imagepath
            }

        }
    }
}