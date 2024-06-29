package com.example.challengeconexa.ui.posts

import com.example.challengeconexa.data.model.Post

interface OnPostClicked {
    fun onPostClicked(post: Post)
}