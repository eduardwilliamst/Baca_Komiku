package com.example.bacakomiku


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.bacakomiku.fragment.FragmentActivity

class MainActivity : AppCompatActivity() {

    private var btnActivity: AppCompatButton? = null
    private var btnFragment: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnActivity = findViewById(R.id.button_to_activity)
        btnFragment = findViewById(R.id.button_to_fragment)

        btnActivity?.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        btnFragment?.setOnClickListener{
            startActivity(Intent(this, FragmentActivity::class.java))
//            Toast.makeText(this, "Sorry, Will be Update Soon!", Toast.LENGTH_SHORT).show()
        }

    }
}