package com.example.bacakomiku

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolderDetail (private val itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private var title_list: AppCompatTextView? = null
    private var title_grid: AppCompatTextView? = null
    private var info: AppCompatTextView? = null
    private var img_grid_genre: AppCompatImageView? = null
    private var img_list_genre: AppCompatImageView? = null

    fun bindData(data: GenreData) {
        title_list = itemView.findViewById(R.id.title_list_genre)
        title_grid = itemView.findViewById(R.id.title_grid_genre)
        info = itemView.findViewById(R.id.tv_info)
        img_grid_genre = itemView.findViewById(R.id.img_grid_genre)
        img_list_genre = itemView.findViewById(R.id.img_list_genre)

        title_list?.text = data.title
        title_grid?.text = data.title
        info?.text = data.info

        if (data.title == "action") {
            img_list_genre?.setImageResource(R.drawable.action1)
            img_grid_genre?.setImageResource(R.drawable.action1)
        } else if (data.title == "comedy") {
            img_list_genre?.setImageResource(R.drawable.comedy1)
            img_grid_genre?.setImageResource(R.drawable.comedy1)
        } else if (data.title == "drama") {
            img_list_genre?.setImageResource(R.drawable.drama1)
            img_grid_genre?.setImageResource(R.drawable.drama1)
        }

        itemView.setOnClickListener {
            val googleUrl = "https://www.google.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl))
            itemView.context.startActivity(intent)
        }


    }

}