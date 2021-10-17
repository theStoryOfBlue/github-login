package com.example.githubapp.ui.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapp.data.model.User
import com.example.githubapp.databinding.ActivityProfileBinding
import com.example.githubapp.utils.Constants.EXTRA_ACCESS_TOKEN

class ProfileActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun startActivity(context: Context, user: User){
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(EXTRA_ACCESS_TOKEN, user)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}