package com.example.githubapp.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favviewModel by viewModels<FavUserViewModel> {
            ViewModelFactory.getInstance(this.application)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavUser.layoutManager = layoutManager

        favviewModel.getAllFavorite().observe(this) { listUser ->
            binding.rvFavUser.adapter = FavoriteAdapter(ArrayList(listUser))
        }
    }
}