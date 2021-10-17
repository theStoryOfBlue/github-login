package com.example.githubapp.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.User
import com.example.githubapp.network.GithubApi
import com.example.githubapp.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val githubApi: GithubApi
): ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories

    fun getUserData(token: String){
        viewModelScope.launch {
            try {
                _userData.value = githubApi.getUserData("bearer $token")
                Log.d(TAG, "getUserData: ${_userData.value}")
            } catch (e: Exception) {
                Log.d(TAG, "getUserData: error $e")
            }
        }
    }

    fun getRepositories(token: String){
        viewModelScope.launch {
            try {
                _repositories.value = githubApi.getRepositories("bearer $token")
            } catch (e: Exception) {
                Log.d(TAG, "getRepos: error $e")
            }
        }
    }
}