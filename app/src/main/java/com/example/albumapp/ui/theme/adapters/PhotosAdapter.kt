package com.example.albumapp.ui.theme.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albumapp.databinding.PhotosItemBinding
import com.example.albumapp.domain.model.photos.PhotosResponseItem

class PhotosAdapter() :
    androidx.recyclerview.widget.ListAdapter<PhotosResponseItem, PhotosAdapter.ViewHolder>(
        PhotosDiffCallBack()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            PhotosItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: PhotosItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(photosResponseItem: PhotosResponseItem) {
            itemBinding.apply {
                Glide.with(itemBinding.root.context)
                    .load(photosResponseItem.url)
                    .into(itemBinding.photoImg)
            }
        }
    }
}

class PhotosDiffCallBack() : DiffUtil.ItemCallback<PhotosResponseItem>() {
    override fun areItemsTheSame(
        oldItem: PhotosResponseItem,
        newItem: PhotosResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PhotosResponseItem,
        newItem: PhotosResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}