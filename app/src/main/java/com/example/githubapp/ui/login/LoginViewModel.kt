package com.example.githubapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.model.AccessToken
import com.example.githubapp.network.GithubApi
import com.example.githubapp.utils.Constants.TAG
import com.example.githubapp.utils.Constants.clientID
import com.example.githubapp.utils.Constants.clientSecret
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val githubApi: GithubApi
): ViewModel() {

    private val _accessToken = MutableLiveData<AccessToken>()
    val accessToken: LiveData<AccessToken>
        get() = _accessToken

    fun getAccessToken(code: String) {
        Log.e(TAG, "getAccessToken: start", )
        viewModelScope.launch {
            try {
                _accessToken.value = githubApi.getAccessToken(clientID, clientSecret, code)
                Log.e(TAG, "AccessToken: ${_accessToken.value?.accessToken}")
            } catch (e: Exception) {
                Log.e(TAG, "getAccessToken: error $e")
            }
        }
    }
}