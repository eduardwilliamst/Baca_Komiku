package com.example.bacakomiku

import androidx.recyclerview.widget.DiffUtil

class RecyclerDiffUtilCallbackDetail: DiffUtil.ItemCallback<KomikData>() {
    override fun areItemsTheSame(oldItem: KomikData, newItem: KomikData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: KomikData, newItem: KomikData): Boolean {
        return oldItem.title == newItem.title
                && oldItem.info == newItem.info
    }

}