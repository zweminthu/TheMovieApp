package com.example.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.adapters.MovieAdapter
import com.example.themovieapp.delegates.MovieViewHolderDelegate
import com.example.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.google.android.material.chip.ChipDrawable.Delegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
//        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}