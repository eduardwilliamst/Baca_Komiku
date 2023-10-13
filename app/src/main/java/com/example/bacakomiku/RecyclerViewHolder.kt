package com.example.bacakomiku

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(
    private val itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private var title_list: AppCompatTextView? = null
    private var title_grid: AppCompatTextView? = null
    private var info: AppCompatTextView? = null
    private var img: AppCompatImageView? = null


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

        val imgResourceId = itemView.context.resources.getIdentifier(data.img, "drawable", itemView.context.packageName)
        img_grid_genre?.setImageResource(imgResourceId)
        img_list_genre?.setImageResource(imgResourceId)

        itemView.setOnClickListener {
            val data = data.title
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.action = Intent.ACTION_SEND
            intent.putExtra("tipe", data)
            intent.type = "text/plain"
            itemView.context.startActivity(intent)
//            Toast.makeText(itemView.context, data, Toast.LENGTH_SHORT).show()
        }

    }

}