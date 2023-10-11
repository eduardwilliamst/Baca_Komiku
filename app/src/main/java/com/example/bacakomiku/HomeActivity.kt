package com.example.bacakomiku

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    private val adapter = GenreAdapter()

    companion object {
        private const val SPAN_COUNT_ONE = 1
        private const val SPAN_COUNT_THREE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        gridLayoutManager = GridLayoutManager(this, SPAN_COUNT_ONE)
        recyclerView = findViewById(R.id.recycler_view_activity)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter // Menggunakan adapter
        adapter.updateData(provideData())

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_switch_layout -> {
                switchLayout()
                switchIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchLayout() {
        if (gridLayoutManager.spanCount == SPAN_COUNT_ONE) {
            gridLayoutManager.spanCount = SPAN_COUNT_THREE
            recyclerView?.layoutManager = gridLayoutManager
        } else {
            gridLayoutManager.spanCount = SPAN_COUNT_ONE
            recyclerView?.layoutManager = LinearLayoutManager(this)
        }
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }


    private fun switchIcon(item: MenuItem) {
        if (gridLayoutManager.spanCount == SPAN_COUNT_THREE) {
            item.setIcon(R.drawable.ic_span_3)
        } else {
            item.setIcon(R.drawable.ic_span_1)
        }
    }

    private fun provideData(): List<GenreData> {
        val data = mutableListOf<GenreData>()
        val genres = listOf("action", "comedy", "drama", "game", "romance")
        val infos = listOf("20 comen", "10 comen", "32 comen", "10 comen", "20 comen")
        val imgs = listOf("20 comen", "10 comen", "32 comen", "10 comen", "20 comen")

        for (i in genres.indices) {
            val genre = genres[i]
            val info = infos[i]
            val img = imgs[i]
            val genreItem = GenreData(genre, info, img)
            data.add(genreItem)
        }

        return data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

}