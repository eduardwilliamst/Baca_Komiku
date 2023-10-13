package com.example.bacakomiku.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bacakomiku.GenreAdapter
import com.example.bacakomiku.GenreData
import com.example.bacakomiku.R
import com.example.bacakomiku.databinding.FragmentGenreBinding

class GenreFragment : Fragment() {

    private var binding: FragmentGenreBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    private val adapter = GenreAdapter()

    companion object {
        private const val SPAN_COUNT_ONE = 1
        private const val SPAN_COUNT_THREE = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        gridLayoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_ONE)
        recyclerView = requireView().findViewById(R.id.recycler_view_fragment_home)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        adapter.updateData(provideData())
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
            recyclerView.layoutManager = gridLayoutManager
        } else {
            gridLayoutManager.spanCount = SPAN_COUNT_ONE
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
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
            genreItem.intentFrag = "yes"
            data.add(genreItem)
        }

        data.reverse()

        return data
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)
    }
}
