package com.example.challengeconexa.ui.users

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.data.model.User
import com.example.challengeconexa.data.repository.JsonPlaceholderRepository
import com.example.challengeconexa.utils.BaseViewModel
import kotlinx.coroutines.launch

class UsersViewModel(application: Application): BaseViewModel(application) {
    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>>
        get() = _usersLiveData

    private val repository = JsonPlaceholderRepository()

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            if (response?.isSuccessful == true) {
                response.body()?.let { users ->
                    _usersLiveData.postValue(users)
                }
            }
            else {
                onError.postValue(Exception(response?.message()))
            }
        }
    }
}