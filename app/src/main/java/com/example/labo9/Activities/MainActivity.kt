package com.example.labo9.Activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.labo9.Constant.AppConstant
import com.example.labo9.Entity.Movie
import com.example.labo9.Fragments.MainContentFragment
import com.example.labo9.Fragments.MainListFragment
import com.example.labo9.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private var resource = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarmain)

        initFragments()
    }

    fun initFragments(){
        mainFragment = MainListFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            resource = R.id.portrait_main_place_holder
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showContent(R.id.land_main_movieviewer_ph, Movie())
            resource = R.id.land_main_place_holder
        }

        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    private fun showContent(id_placeholder: Int, movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(id_placeholder, mainContentFragment)
    }

    override fun managePortraitItemClick(movie: Movie) = showContent(R.id.portrait_main_place_holder, movie)

    override fun managedLandscapeItemClick(movie: Movie) = showContent(R.id.land_main_movieviewer_ph, movie)
}
