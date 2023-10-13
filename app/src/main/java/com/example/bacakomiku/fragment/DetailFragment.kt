package com.example.bacakomiku.fragment

import android.os.Bundle
import android.util.Log
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
import com.example.bacakomiku.DetailAdapter
import com.example.bacakomiku.Global
import com.example.bacakomiku.KomikData
import com.example.bacakomiku.R
import com.example.bacakomiku.RecyclerViewHolder
import com.example.bacakomiku.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    private val adapter = DetailAdapter()

    companion object {
        private const val SPAN_COUNT_ONE = 1
        private const val SPAN_COUNT_THREE = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        gridLayoutManager = GridLayoutManager(requireContext(), SPAN_COUNT_ONE)
        recyclerView = requireView().findViewById(R.id.recycler_view_fragment_detail)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter


//        val title = DetailFragmentArgs.fromBundle(arguments as Bundle).title
        val title = arguments?.getString(RecyclerViewHolder.EXTRA_TITLE)
        Log.d("MyApp", title.toString())
        adapter.updateData(provideData(title ?: ""))
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

    private fun provideData(title : String): List<KomikData> {
        val data = mutableListOf<KomikData>()

        for (komik in Global.dataKomik) {
            Log.d("MyApp", "Nilai tipe dari komik: ${komik.tipe}")
            if (title == komik.tipe) {
                Log.d("MyApp", "Menambahkan data ke list")
                data.add(
                    KomikData(
                    tipe = komik.tipe,
                    title = komik.title,
                    info = komik.info,
                    img = komik.img,
                    link = komik.link
                )
                )
            }
        }

        return data
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)
    }
}
