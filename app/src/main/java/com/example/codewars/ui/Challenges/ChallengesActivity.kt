package com.example.codewars.ui.Challenges

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import com.example.codewars.R
import com.example.codewars.ui.Challenges.AuthoredChallenges.AuthoredChallengesFragment
import com.example.codewars.ui.Challenges.completedChallenges.ChallengesFragment
import com.example.codewars.ui.Challenges.detailsChallenges.DetailsChallengesFragment
import com.example.codewars.util.Constants
import com.example.codewars.util.Constants.USER_NAME_TAG
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_challenges.*

class ChallengesActivity : DaggerAppCompatActivity(), LifecycleOwner,
    OnFragmentChallengesInteractionListener {

    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private lateinit var fragmentTransaction : FragmentTransaction
    private var user: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)
        user = if(savedInstanceState == null){
            val extras: Bundle? = intent.extras
            extras?.getString(USER_NAME_TAG)
        } else {
            savedInstanceState.getSerializable(USER_NAME_TAG) as String
        }

        goToChallengesFragment(user)

        initListener()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        bottom_navigation.visibility = View.VISIBLE
    }

    private fun initListener() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.complete_challenges_item_bottom -> {
                    goToChallengesFragment(user)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.authored_challenges_item_bottom -> {
                    goToAuthoredChallengesFragment(user)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }


    override fun goToAuthoredChallengesFragment(user: String?) {
        replaceFragment(AuthoredChallengesFragment.newInstance(user))
    }

    override fun goToChallengesFragment(user: String?) {
        replaceFragment(ChallengesFragment.newInstance(user))
    }

    override fun goToDetailsChallengeFragment(id: String?) {
        replaceFragment(DetailsChallengesFragment.newInstance(id))
        bottom_navigation.visibility = View.GONE
    }

    private fun replaceFragment(fragment : Fragment) {
        fragment.let { fragmentToReplace ->
            if (fragmentToReplace.isVisible.not()) {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content_fragment_challenge, fragmentToReplace,
                    Constants.USER_FRAGMENT_TAG
                )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }
}