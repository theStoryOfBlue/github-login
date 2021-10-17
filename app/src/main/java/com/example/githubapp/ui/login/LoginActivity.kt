package com.example.githubapp.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.githubapp.databinding.ActivityLoginBinding
import com.example.githubapp.ui.repo.RepositoryActivity
import com.example.githubapp.utils.Constants.TAG
import com.example.githubapp.utils.Constants.clientID
import com.example.githubapp.utils.Constants.oauthLoginURL
import com.example.githubapp.utils.Constants.redirectUri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnLogin.setOnClickListener { processLogin() }
        Log.d(TAG, "onCreate: ini muncul ga?")

        viewModel.accessToken.observe(this, { accessToken ->
            RepositoryActivity.startActivity(this, accessToken)
        })
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent?.data
        Log.d(TAG, "onResume: uri ${intent?.data}")
        if (uri != null){
            val code = uri.getQueryParameter("code")
            Log.d(TAG, "onResume: code $code")
            if(code != null){
                setLoadingVisibility(true)
                viewModel.getAccessToken(code)
                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
            } else if((uri.getQueryParameter("error")) != null){
                setLoadingVisibility(false)
                Log.d(TAG, "onResume: error")
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoadingVisibility(isVisible: Boolean){
        binding.pbLogin.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun processLogin() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
            "$oauthLoginURL?client_id=$clientID&scope=repo%20user"))

        startActivity(intent)
    }
}