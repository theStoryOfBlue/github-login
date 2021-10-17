package com.example.githubapp.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapp.data.model.User
import com.example.githubapp.databinding.ActivityProfileBinding
import com.example.githubapp.utils.Constants.EXTRA_USER_DATA

class ProfileActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun startActivity(context: Context, user: User?){
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(EXTRA_USER_DATA, user)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProfileData()
        binding.btnLogout.setOnClickListener { logout() }
    }

    private fun logout() {

    }

    private fun setupProfileData() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER_DATA)
        binding.tvName.text = user?.name
        binding.tvUsername.text = user?.username
        binding.tvEmail.text = user?.email
        binding.tvLocation.text = user?.location
        binding.tvBio.text = user?.bio
        binding.tvFollowers.text = user?.followers.toString()
        binding.tvFollowing.text = user?.following.toString()
    }
}