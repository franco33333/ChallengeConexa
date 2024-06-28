package com.example.challengeconexa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeconexa.R
import com.example.challengeconexa.databinding.ItemPostBinding
import com.example.challengeconexa.data.objects.Post

class PostsAdapter(private val list: List<Post>, val context: Context):
    RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    lateinit var onPostClicked: (Post) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
    ) {
        private val binding = ItemPostBinding.bind(itemView)

        fun bind(data: Post) {
            with(binding) {
                Glide.with(root.context)
                    .load(data.thumbnail)
                    .circleCrop()
                    .placeholder(R.drawable.ic_error)
                    .into(ivThumbnail)

                tvTitle.text = data.title
                tvContent.text = data.content
                cvPost.setOnClickListener { onPostClicked(data) }
            }
        }
    }
}