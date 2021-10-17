package com.example.githubapp.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getUserData(token: String){
        viewModelScope.launch {
            try {
                _userData.value = githubApi.getUserData(token)
            } catch (e: Exception) {
                Log.d(TAG, "getUserData: error $e")
            }
        }
    }
}