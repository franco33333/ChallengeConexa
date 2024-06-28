package com.example.challengeconexa.ui.posts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.data.objects.Post
import com.example.challengeconexa.data.remote.ApiClient
import com.example.challengeconexa.data.remote.ApiClient.callApi
import com.example.challengeconexa.utils.BaseViewModel
import kotlinx.coroutines.launch

class PostsViewModel(application: Application): BaseViewModel(application) {
    private val _postsLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>>
        get() = _postsLiveData

    fun getPosts() {
        viewModelScope.launch {
            ApiClient.service::getPosts.callApi().collect {
                if (it.isSuccess){
                    it.getOrNull()?.let { posts ->
                        _postsLiveData.postValue(posts)
                    }
                } else
                    onError.postValue(it.exceptionOrNull())
            }
        }
    }
}