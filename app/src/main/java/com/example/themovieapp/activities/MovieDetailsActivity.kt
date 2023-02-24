package com.example.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovieapp.R
import com.example.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }

    // View Pods
    lateinit var actorsViewPod : ActorListViewPod
    lateinit var creatorsViewPod : ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpViewPods()
        setUpBackListeners()

    }

    private fun setUpBackListeners(){
        btnBack.setOnClickListener(){
            super.onBackPressed()
        }
    }

    private fun setUpViewPods(){
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }
}