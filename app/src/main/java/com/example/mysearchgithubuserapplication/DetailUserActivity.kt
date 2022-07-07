package com.example.mysearchgithubuserapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_user.*


class DetailUserActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        supportActionBar!!.hide()

        val userName = intent.getStringExtra("USERNAME")!!
        val userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)
        userViewModel.setUserDetail(userName)
        userViewModel.getUserDetail().observe(
            this
        ) { item ->
            Glide.with(applicationContext).load(item.avatarUrl).into(image_user)
            tv_type.text = item.type
            tv_username.text = item.login
            tv_name.text = item.name
            tv_location.text = item.location
            tv_bio.text = item.bio
            tv_repos.text = item.publicRepos
            tv_gist.text = item.publicGists
            tv_follower.text = item.followers
            tv_following.text = item.following
            tv_created.text = "Created At "+item.createdAt.substring(0,10)+" "+item.createdAt.substring(11,19)
            tv_updated.text = "Updated At "+item.updatedAt.substring(0,10)+" "+item.updatedAt.substring(11,19)
        }
    }
}