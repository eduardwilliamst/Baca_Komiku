package com.example.bacakomiku

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

class DetailAdapter : RecyclerView.Adapter<RecyclerViewHolderDetail>() {

    private val differ = AsyncListDiffer(this, RecyclerDiffUtilCallback())
    private val VIEW_TYPE_SMALL = 1
    private val VIEW_TYPE_BIG = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderDetail {
        val layoutRes = if (viewType == VIEW_TYPE_BIG) {
            R.layout.genre_grid
        } else {
            R.layout.genre_list
        }

        val itemView = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return RecyclerViewHolderDetail(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_BIG
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderDetail, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<GenreData>) {
        differ.submitList(data)
    }
}