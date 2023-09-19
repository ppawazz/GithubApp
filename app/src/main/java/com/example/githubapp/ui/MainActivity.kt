package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeData()
    }

    private fun setupUI() {
        userAdapter = UserAdapter(this)
        binding.rvUser.adapter = userAdapter
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView.editText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = textView.text.toString()
                viewModel.searchUsers(query)
                showLoading(true)
                binding.searchView.hide()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun observeData() {
        viewModel.getUsersLiveData().observe(this) { githubResponse ->
            showLoading(false)
            githubResponse?.let {
                userAdapter.submitList(it)
            }
            Log.d("TAG", "response: $githubResponse")
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}