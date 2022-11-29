package com.example.githubapp.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.githubapp.data.pref.SharedPref
import com.example.githubapp.databinding.ActivityLoginBinding
import com.example.githubapp.databinding.CustomDialogLoadingBinding
import com.example.githubapp.ui.repo.RepositoryActivity
import com.example.githubapp.utils.Constants.TAG
import com.example.githubapp.utils.Constants.clientID
import com.example.githubapp.utils.Constants.oauthLoginURL
import com.example.githubapp.utils.Constants.redirectUri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPref
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedPref = SharedPref(this)

        binding.btnLogin.setOnClickListener { processLogin() }

        viewModel.accessToken.observe(this, { accessToken ->
            RepositoryActivity.startActivity(this, accessToken.accessToken)
            sharedPref.accessToken = accessToken.accessToken
        })
    }

    override fun onResume() {

        super.onResume()

        val uri: Uri? = intent?.data

        Log.e(TAG, "onResume: start", )



        if (uri != null){
            val code = uri.getQueryParameter("code")
            binding.textView.text = code
            Log.e(TAG, "onResume code : $code", )
//            if(code != null){
//                showDialog()
//                viewModel.getAccessToken(code)
//                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
//            } else if((uri.getQueryParameter("error")) != null){
//                Log.d(TAG, "error: ${uri.getQueryParameter("error")}")
//                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun processLogin() {
        Log.e(TAG, "processLogin startr", )
        //showDialog()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
            "$oauthLoginURL?client_id=$clientID"))
        Log.e(TAG, "processLogin: ${intent.data}")
        startActivity(intent)
    }

    private fun showDialog(){
        val dialogBinding = CustomDialogLoadingBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setView(dialogBinding.root)
            .create()
            .show()
    }
}