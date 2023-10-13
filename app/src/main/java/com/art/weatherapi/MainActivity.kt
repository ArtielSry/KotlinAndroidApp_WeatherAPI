package com.art.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.art.weatherapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // key = a76bf026144b68ef567ca88a8946bcf2
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {

    }
}
