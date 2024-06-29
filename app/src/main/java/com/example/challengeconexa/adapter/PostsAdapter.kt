package com.example.challengeconexa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeconexa.R
import com.example.challengeconexa.databinding.ItemPostBinding
import com.example.challengeconexa.data.model.Post
import com.example.challengeconexa.ui.posts.OnPostClicked

class PostsAdapter(private val list: List<Post>, val onPostClicked: OnPostClicked) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindPost(list[position])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    ) {
        private val binding = ItemPostBinding.bind(itemView)

        fun bindPost(data: Post) {
            with(binding) {
                Glide.with(root.context)
                    .load(data.thumbnail)
                    .circleCrop()
                    .placeholder(R.drawable.ic_error)
                    .into(ivThumbnail)

                tvTitle.text = data.title
                tvContent.text = data.content
                cvPost.setOnClickListener { onPostClicked.onPostClicked(data) }
            }
        }
    }
}