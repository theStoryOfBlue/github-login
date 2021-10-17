package com.example.githubapp.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.model.User
import com.example.githubapp.network.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val githubApi: GithubApi
): ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData
}