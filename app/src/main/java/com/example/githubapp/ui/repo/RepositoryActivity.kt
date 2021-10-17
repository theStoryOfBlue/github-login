package com.example.githubapp.ui.repo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityRepositoryBinding
import com.example.githubapp.data.model.AccessToken
import com.example.githubapp.data.model.User
import com.example.githubapp.ui.profile.ProfileActivity
import com.example.githubapp.utils.Constants.EXTRA_ACCESS_TOKEN
import com.example.githubapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun startActivity(context: Context, accessToken: AccessToken){
            val intent = Intent(context, RepositoryActivity::class.java)
            intent.putExtra(EXTRA_ACCESS_TOKEN, accessToken)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityRepositoryBinding
    private val viewModel by viewModels<RepositoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accessToken = intent?.getParcelableExtra<AccessToken>(EXTRA_ACCESS_TOKEN)
        binding.tvAccessToken.text = accessToken?.accessToken
        Log.d(TAG, "repo activity: token ${accessToken?.accessToken}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_open_profile -> {
                navigateToProfile(viewModel.userData.value)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToProfile(userData: User?) {
        ProfileActivity.startActivity(this, userData)
    }
}