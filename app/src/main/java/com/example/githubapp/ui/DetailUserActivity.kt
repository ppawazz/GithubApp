package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel: DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        if (username != null) {
            viewModel.getUserDetail(username)
            viewModel.userDetailLiveData.observe(this) { userDetail ->
                // Update UI dengan data userDetail
                binding.tvName.text = userDetail.name
                binding.tvUsername.text = userDetail.login
                // Tambahkan kode untuk menampilkan data lainnya
            }
        }
        viewModel.userDetailLiveData.observe(this) { userDetail ->
            // Mengambil jumlah pengikut dan jumlah yang diikuti dari userDetail
            val followersCount = userDetail.followers
            val followingCount = userDetail.following

            // Mengatur teks "Followers" dengan jumlah pengikut
            val followersText = "$followersCount Followers"
            binding.tvFollowers.text = followersText

            // Mengatur teks "Following" dengan jumlah yang diikuti
            val followingText = "$followingCount Following"
            binding.tvFollowing.text = followingText

            // Mengatur teks lainnya seperti nama dan username
            binding.tvName.text = userDetail.name
            binding.tvUsername.text = userDetail.login

            // Memuat gambar avatar menggunakan Glide
            Glide.with(this)
                .load(userDetail.avatarUrl)
                .into(binding.ivProfil)
        }
    }
}