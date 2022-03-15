package com.example.thecatapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.my_nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button)
        floatingActionButton.setOnClickListener {
            navController.navigate(R.id.uploadFragment)
        }
    }
}