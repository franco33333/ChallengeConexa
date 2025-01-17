package com.example.challengeconexa.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.challengeconexa.adapter.UsersAdapter
import com.example.challengeconexa.data.model.User
import com.example.challengeconexa.databinding.FragmentUsersBinding
import com.example.challengeconexa.ui.users.map.MapActivity
import com.example.challengeconexa.utils.gone
import com.example.challengeconexa.utils.visible

class UsersFragment : Fragment(), OnUserClicked {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java].apply {
            usersLiveData.observe(this@UsersFragment) { user ->
                binding.progressBar.gone()
                binding.tvError.gone()
                binding.rvUsers.adapter = UsersAdapter(user, this@UsersFragment)
            }
            onError.observe(this@UsersFragment) {
                binding.progressBar.gone()
                binding.rvUsers.adapter = null
                binding.tvError.visible()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible()
        viewModel.getUsers()
    }

    override fun onUserClicked(user: User) {
        startActivity(MapActivity.getIntent(requireContext(), user))
    }
}