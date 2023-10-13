package com.example.bacakomiku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    private val adapter = DetailAdapter()

    companion object {
        private const val SPAN_COUNT_ONE = 1
        private const val SPAN_COUNT_THREE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        gridLayoutManager = GridLayoutManager(this, SPAN_COUNT_ONE)
        recyclerView = findViewById(R.id.recycler_view_detail)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

        val intent: Intent = intent

        adapter.updateData(provideData(intent))

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

    private fun provideData(intent: Intent): List<KomikData> {
        val data = mutableListOf<KomikData>()
        val tipe = intent.getStringExtra("tipe")

        Log.d("MyApp", "Nilai tipe dari Intent: $tipe")

        for (komik in Global.dataKomik) {
            Log.d("MyApp", "Nilai tipe dari komik: ${komik.tipe}")
            if (tipe == komik.tipe) {
                Log.d("MyApp", "Menambahkan data ke list")
                data.add(KomikData(
                    tipe = komik.tipe,
                    title = komik.title,
                    info = komik.info,
                    img = komik.img,
                    link = komik.link
                ))
            }
        }

        return data
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }
}