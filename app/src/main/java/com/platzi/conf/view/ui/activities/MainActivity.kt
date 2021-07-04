package com.platzi.conf.view.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.platzi.conf.R
import com.platzi.conf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setActionBar(binding.toolbarMain)
        configNav()

    }

    private fun configNav() {
        NavigationUI.setupWithNavController(binding.bnMenu,Navigation.findNavController(this,R.id.fragContenedor))
    }
}