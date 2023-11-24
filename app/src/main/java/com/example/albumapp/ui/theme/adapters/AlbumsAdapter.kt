package com.example.albumapp.ui.theme.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.albumapp.databinding.AlbumItemBinding
import com.example.albumapp.domain.model.album.AlbumResponseItem

class AlbumsAdapter() :
    ListAdapter<AlbumResponseItem, AlbumsAdapter.ViewHolder>(AlbumDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class ViewHolder(private val itemBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(albumResponseItem: AlbumResponseItem) {
            itemBinding.apply {
                albumNameTv.text = albumResponseItem.title
                albumCv.setOnClickListener {

                }
            }
        }
    }


}

class AlbumDiffCallBack : DiffUtil.ItemCallback<AlbumResponseItem>() {
    override fun areItemsTheSame(
        oldItem: AlbumResponseItem,
        newItem: AlbumResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AlbumResponseItem,
        newItem: AlbumResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}

