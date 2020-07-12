package com.example.codewars.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import com.example.codewars.R
import com.example.codewars.ui.User.UserFragment
import com.example.codewars.util.Constants.USER_FRAGMENT_TAG
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), LifecycleOwner, OnFragmentInteractionListener {

    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(USER_FRAGMENT_TAG)
    }

    override fun goToUserFragment() {

    }

    private fun replaceFragment(fragmentCode : String) {
        val fragment : Fragment? = when (fragmentCode){

            USER_FRAGMENT_TAG -> {
                fragmentManager.findFragmentByTag(USER_FRAGMENT_TAG) ?: UserFragment.newInstance()
            }


            else -> null
        }

        fragment?.let { fragmentToReplace ->
            if (fragmentToReplace.isVisible.not()) {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content_fragment, fragmentToReplace, fragmentCode)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }


}