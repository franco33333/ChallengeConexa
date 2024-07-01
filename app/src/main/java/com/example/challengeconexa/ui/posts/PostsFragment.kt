package com.example.challengeconexa.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.challengeconexa.adapter.PostsAdapter
import com.example.challengeconexa.data.model.Post
import com.example.challengeconexa.databinding.FragmentPostsBinding
import com.example.challengeconexa.ui.posts.detail.PostDetailActivity
import com.example.challengeconexa.utils.gone
import com.example.challengeconexa.utils.visible


class PostsFragment : Fragment(), OnPostClicked {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[PostsViewModel::class.java].apply {
            postsLiveData.observe(this@PostsFragment) { post ->
                binding.progressBar.gone()
                binding.tvError.gone()
                binding.tvNoResults.gone()
                val adapter = PostsAdapter(post, this@PostsFragment)
                binding.rvPosts.adapter = adapter
            }
            postsFilteredLiveData.observe(this@PostsFragment) { post ->
                binding.progressBar.gone()
                if (post.isNotEmpty()) {
                    binding.tvNoResults.gone()
                    binding.tvError.gone()
                    val adapter = PostsAdapter(post, this@PostsFragment)
                    binding.rvPosts.adapter = adapter
                } else {
                    binding.tvNoResults.visible()
                    val adapter = PostsAdapter(post,  this@PostsFragment)
                    binding.rvPosts.adapter = adapter
                }
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
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible()
        viewModel.getPosts()

        binding.ivSearch.setOnClickListener {
            search()
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            true
        }
    }

    private fun search() {
        binding.rvPosts.smoothScrollToPosition(0)
        binding.tvNoResults.gone()
        binding.tvError.gone()
        binding.progressBar.visible()
        viewModel.getPostsBySearch(binding.etSearch.text.toString())
    }

    override fun onPostClicked(post: Post) {
        startActivity(PostDetailActivity.getIntent(requireContext(), post))
    }
}