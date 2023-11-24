package com.example.albumapp.ui.theme.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.albumapp.R
import com.example.albumapp.databinding.ActivityMainBinding
import com.example.albumapp.ui.theme.fragments.UserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userFragment = UserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}