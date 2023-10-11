package com.example.bacakomiku

import androidx.recyclerview.widget.DiffUtil

class RecyclerDiffUtilCallback : DiffUtil.ItemCallback<GenreData>() {
    override fun areItemsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
        return oldItem.title == newItem.title
                && oldItem.info == newItem.info
    }

}