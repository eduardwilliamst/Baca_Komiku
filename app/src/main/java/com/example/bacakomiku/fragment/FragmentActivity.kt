package com.example.bacakomiku.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.bacakomiku.R
import com.example.bacakomiku.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private var binding: ActivityFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        navController = findNavController(R.id.nav_container)

//        navController?.let { navController -> setupActionBarWithNavController(navController) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() == true || super.onSupportNavigateUp()
    }
}