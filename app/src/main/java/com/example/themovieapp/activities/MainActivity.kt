package com.example.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.adapters.BannerAdapter
import com.example.themovieapp.adapters.ShowcaseAdapter
import com.example.themovieapp.data.models.MovieModel
import com.example.themovieapp.data.models.MovieModelImpl
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.delegates.BannerViewHolderDelegate
import com.example.themovieapp.delegates.MovieViewHolderDelegate
import com.example.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.example.themovieapp.viewpods.ActorListViewPod
import com.example.themovieapp.viewpods.MovieListViewPod
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter : ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod : MovieListViewPod
    lateinit var mMoviesByGenreViewPod : MovieListViewPod

    lateinit var mActorListViewPod: ActorListViewPod

    private val mMovieModel : MovieModel = MovieModelImpl

    private var mGenres : List<GenreVO>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpBannerViewPager()
//        setUpGenreTabLayout()
        setUpListeners()
        setUpShowcaseRecyclerView()
        setUpViewPods()

//        MovieDataAgentImpl.getNowPlayingMovies()
//        OkHttpDataAgentImpl.getNowPlayingMovies()
//        RetrofitDataAgentImpl.getNowPlayingMovies()

        requestData()

    }

    private fun requestData() {
        mMovieModel.getNowPlayingMovies(
            onSuccess = {
                mBannerAdapter.setNewData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        mMovieModel.getPopularMovies(
            onSuccess = {
                mBestPopularMovieListViewPod.setData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        mMovieModel.getTopRatedMovies(
            onSuccess = {
                mShowcaseAdapter.setNewData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                setUpGenreTabLayout(it)

                // Get Movies by Genre for 1st Genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }

            },
            onFailure = {
                showError(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {
                showError(it)
            }
        )
    }

    private fun getMoviesByGenre(genreId: Int){
        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
        onSuccess = {
            mMoviesByGenreViewPod.setData(it)
        }, onFailure = {
                showError(it)
            })
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorList as ActorListViewPod
        mActorListViewPod.setUpActorViewPod(backgroundColorReference = R.color.colorPrimary, titleText = getString(R.string.lbl_actors),
            moreTitleText = "")
    }

    private fun setUpShowcaseRecyclerView() {

        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
                //        applicationContext || this
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTapMovieFromBanner(movieId : Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId : Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId : Int){
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    private fun showError(it: String){
        Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
    }

}