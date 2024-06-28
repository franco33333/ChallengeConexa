package com.example.challengeconexa.ui.posts.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.challengeconexa.R
import com.example.challengeconexa.data.objects.Post
import com.example.challengeconexa.databinding.ActivityPostDetailBinding
import com.google.gson.Gson

class PostDetailActivity : AppCompatActivity() {

    companion object {
        private const val ARG_POST = "ARG_POST"
        fun getIntent(context: Context, post: Post) =
            Intent(context, PostDetailActivity::class.java)
                .putExtra(ARG_POST, Gson().toJson(post))
    }

    private val binding by lazy { ActivityPostDetailBinding.inflate(layoutInflater) }
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar()

        intent.getStringExtra(ARG_POST).let { post = Gson().fromJson(it, Post::class.java) }
        with(binding) {
            Glide.with(root.context)
                .load(post.image)
                .placeholder(R.drawable.ic_error)
                .into(ivPost)
            tvTitle.text = post.title
            tvDescription.text = post.content
            tvDate.text = post.updatedAt
        }
    }

    private fun setupToolbar() {
        binding.containerToolbar.toolbar.title = "Post Detail"
        binding.containerToolbar.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.containerToolbar.toolbar)
        binding.containerToolbar.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}