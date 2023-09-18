package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.data.response.GithubResponse
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)
        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        viewModel.getUsersLiveData().observe(this) { githubResponse ->
            showLoading(false)
            if (githubResponse != null) {
                binding.rvUser.adapter = UserAdapter(). apply {
                    submitList(githubResponse)
                }
            }
            Log.d("TAG", "response: $githubResponse")
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.searchUsers(textView.text.toString())
                    showLoading(true)
                    searchView.hide()
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }
}