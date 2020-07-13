package com.example.codewars.ui.User

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import com.example.codewars.R
import com.example.codewars.ui.Challenges.ChallengesActivity
import com.example.codewars.util.Constants.USER_FRAGMENT_TAG
import com.example.codewars.util.Constants.USER_NAME_TAG
import dagger.android.support.DaggerAppCompatActivity

class UserActivity : DaggerAppCompatActivity(), LifecycleOwner,
    OnFragmentInteractionListener {

    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToUserFragment()
    }

    override fun goToUserFragment() {
        replaceFragment(UserFragment.newInstance())
    }

    override fun gotToChallengesActivity(user: String, fragment: UserFragment) {
        val intent = Intent(this,
            ChallengesActivity::class.java)
        intent.putExtra(USER_NAME_TAG, user)
        startActivity(intent)
    }

    private fun replaceFragment(fragment : Fragment) {
        fragment.let { fragmentToReplace ->
            if (fragmentToReplace.isVisible.not()) {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content_fragment, fragmentToReplace, USER_FRAGMENT_TAG)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }


}