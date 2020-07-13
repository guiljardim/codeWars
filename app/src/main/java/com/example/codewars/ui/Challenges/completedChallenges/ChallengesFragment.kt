package com.example.codewars.ui.Challenges.completedChallenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codewars.R
import com.example.codewars.data.model.CompletedChallengeData
import com.example.codewars.util.Constants.NAME_USER
import com.example.codewars.util.ViewData
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.challenges_fragment.*
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject


class ChallengesFragment : DaggerFragment() {

    companion object {
        fun newInstance(user: String?) =
            ChallengesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(NAME_USER, user)
                }
            }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val challengesViewModel: ChallengesViewModel by viewModels {
        viewModelFactory
    }

    private var nameUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            nameUser = it?.getString(NAME_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(challengesViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            completedChallengeObserver(this)
            this.getCompletedChallenge(nameUser, 1)
        }

        return inflater.inflate(R.layout.challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    private fun completedChallengeObserver(challengesViewModel: ChallengesViewModel){
        challengesViewModel.completedChallengeLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when(it.status){
                    ViewData.Status.LOADING ->  {
                        progress_bar_challenge_fragment.visibility = View.VISIBLE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_challenge_fragment.visibility = View.GONE
                        it.data?.data?.let { data -> createChallengeList(data) }
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_challenge_fragment.visibility = View.GONE
                    }


                }
            }
        )
    }

    private fun createChallengeList(listOfChallenges: List<CompletedChallengeData>) {
        recycle_view_challenge_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_challenge_fragment.adapter = CompletedChallengeAdapter(listOfChallenges, context)
    }

}