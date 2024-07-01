package com.example.challengeconexa.ui.posts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.data.model.Post
import com.example.challengeconexa.data.repository.JsonPlaceholderRepository
import com.example.challengeconexa.utils.BaseViewModel
import kotlinx.coroutines.launch

class PostsViewModel(application: Application): BaseViewModel(application) {
    private val _postsLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>>
        get() = _postsLiveData

    private val _postsFilteredLiveData = MutableLiveData<List<Post>>()
    val postsFilteredLiveData: LiveData<List<Post>>
        get() = _postsFilteredLiveData

    private val repository = JsonPlaceholderRepository()

    private lateinit var allPosts: List<Post>

    fun getPosts() {
        viewModelScope.launch {
            if (!::allPosts.isInitialized) {
                val response = repository.getPosts()
                if (response?.isSuccessful == true) {
                    response.body()?.let { posts ->
                        allPosts = posts
                        _postsLiveData.postValue(posts)
                    }
                } else {
                    onError.postValue(Exception(response?.message()))
                }
            }
        }
    }

    fun getPostsBySearch(search: String) {
        viewModelScope.launch {
            if (allPosts.isEmpty()) {
                _postsFilteredLiveData.postValue(listOf())
            }
            val filteredPosts = allPosts.filter { post ->
                post.title?.contains(search, ignoreCase = true) ?: false ||
                        post.content?.contains(search, ignoreCase = true) ?: false
            }
            _postsFilteredLiveData.postValue(filteredPosts)
        }
    }
}