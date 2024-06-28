package com.example.challengeconexa.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.challengeconexa.adapter.PostsAdapter
import com.example.challengeconexa.databinding.FragmentPostsBinding
import com.example.challengeconexa.ui.posts.detail.PostDetailActivity
import com.example.challengeconexa.utils.gone
import com.example.challengeconexa.utils.visible


class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java].apply {
            postsLiveData.observe(this@PostsFragment) { post ->
                binding.progressBar.gone()
                binding.tvError.gone()
                val adapter = PostsAdapter(post, requireContext())
                adapter.onPostClicked = { startActivity(PostDetailActivity.getIntent(requireContext(), it)) }
                binding.rvPosts.adapter = adapter
            }
            onError.observe(this@PostsFragment) {
                binding.progressBar.gone()
                binding.rvPosts.adapter = null
                binding.tvError.visible()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible()
        viewModel.getPosts()
    }
}