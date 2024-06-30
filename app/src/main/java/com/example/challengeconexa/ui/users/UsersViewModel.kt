package com.example.challengeconexa.ui.users

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.data.model.User
import com.example.challengeconexa.data.remote.ApiClient
import com.example.challengeconexa.data.remote.ApiClient.callApi
import com.example.challengeconexa.utils.BaseViewModel
import kotlinx.coroutines.launch

class UsersViewModel(application: Application): BaseViewModel(application) {
    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>>
        get() = _usersLiveData

    fun getUsers() {
        viewModelScope.launch {
            ApiClient.service::getUsers.callApi().collect {
                if (it.isSuccess){
                    it.getOrNull()?.let { users ->
                        _usersLiveData.value = users
                    }
                } else
                    onError.postValue(it.exceptionOrNull())
            }
        }
    }
}