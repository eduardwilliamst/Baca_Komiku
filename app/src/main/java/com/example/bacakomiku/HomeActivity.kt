package com.example.bacakomiku

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
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

        val toolbar = findViewById<Toolbar>(R.id.toolbar_activity_home)
        setSupportActionBar(toolbar)
    }
    private fun showSortByDialog() {
        val sortOptions = arrayOf("A-Z", "Z-A")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sort By")
        builder.setItems(sortOptions) { dialog, which ->
            when (which) {
                0 -> {
                    val sortedData = provideData().sortedBy { it.title }
                    adapter.updateData(sortedData)
                }
                1 -> {

                    val sortedData = provideData().sortedByDescending { it.title }
                    adapter.updateData(sortedData)
                }
            }
        }
        builder.show()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_switch_layout -> {
                switchLayout()
                switchIcon(item)
                true
            }
            R.id.menu_sort_by -> {
                showSortByDialog()
                return true
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
        val genres = listOf("Action", "Comedy", "Drama", "Game", "Romance", "Adventure", "Medical", "Shounen", "Slice of Life", "Murim")
        val infos = listOf("5 komik", "5 komik", "5 komik", "5 komik", "5 komik", "3 Komik", "3 Komik", "3 Komik", "3 Komik", "3 Komik")
        val imgs = listOf("action1", "comedy1", "drama1", "game1", "romance1","adventure1", "medical1", "shounen1", "sol1", "murim1")

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