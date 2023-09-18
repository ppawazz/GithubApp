package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}