package com.example.githubapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {

    private val viewModel: DetailUserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_POSITION) ?: 1
        val username = arguments?.getString(ARG_USERNAME) ?: ""

        userAdapter = UserAdapter(requireContext())
        binding.rvFollow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }

        if (position == 1) {
            viewModel.getFollowers(username)
            viewModel.followersLiveData.observe(viewLifecycleOwner) { followersList ->
                userAdapter.submitList(followersList)
            }
        } else {
            viewModel.getFollowing(username)
            viewModel.followingLiveData.observe(viewLifecycleOwner) { followingList ->
                userAdapter.submitList(followingList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}