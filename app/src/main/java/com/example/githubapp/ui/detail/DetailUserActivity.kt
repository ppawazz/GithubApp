package com.example.githubapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.data.database.FavoriteUser
import com.example.githubapp.databinding.ActivityDetailUserBinding
import com.example.githubapp.ui.favorite.FavUserViewModel
import com.example.githubapp.ui.favorite.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    private var favoriteUser: FavoriteUser? = null
    private val viewModel: DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favviewModel by viewModels<FavUserViewModel> {
            ViewModelFactory.getInstance(this.application)
        }
        favoriteUser = FavoriteUser()

        val username = intent.getStringExtra(USERNAME)
        if (username != null) {
            viewModel.getUserDetail(username)
            viewModel.userDetailLiveData.observe(this) { userDetail ->
                binding.tvName.text = userDetail.name
                binding.tvUsername.text = userDetail.login

                favoriteUser.let {
                    favoriteUser?.username = userDetail.login.toString()
                    favoriteUser?.avatarUrl = userDetail.avatarUrl.toString()
                }

                val followersCount = userDetail.followers
                val followingCount = userDetail.following

                val followersText = "$followersCount Followers"
                binding.tvFollowers.text = followersText

                val followingText = "$followingCount Following"
                binding.tvFollowing.text = followingText

                Glide.with(this)
                    .load(userDetail.avatarUrl)
                    .into(binding.ivProfil)

                favviewModel.isFavorited(username).observe(this) {
                    if (it != null) {
                        binding.fab.setImageResource(R.drawable.ic_favo)
                        binding.fab.setOnClickListener {
                            favviewModel.deleteFavUser(username)
                            Toast.makeText(
                                this,
                                "Berhasil di hapus dari favorit",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        val user = FavoriteUser(userDetail.login as String, userDetail.avatarUrl)
                        binding.fab.setImageResource(R.drawable.ic_unfavo)
                        binding.fab.setOnClickListener {
                            favviewModel.insertFavUser(user)
                            Toast.makeText(
                                this,
                                "Berhasil di tambahkan ke favorit",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }

        viewModel.errorLiveData.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        if (username != null) {
            sectionsPagerAdapter.username = username
        }

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_follower)
                1 -> getString(R.string.tab_following)
                else -> ""
            }
        }.attach()

    }

    companion object {
        const val USERNAME = "username_extra"
    }
}