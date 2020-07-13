package com.example.codewars.ui.Challenges.detailsChallenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codewars.R
import com.example.codewars.data.model.Challenge
import com.example.codewars.ui.Challenges.completedChallenges.ChallengesViewModel
import com.example.codewars.util.Constants
import com.example.codewars.util.Constants.ID_USER
import com.example.codewars.util.ViewData
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.challenges_fragment.*
import kotlinx.android.synthetic.main.details_challenges_fragment.*
import javax.inject.Inject

class DetailsChallengesFragment : DaggerFragment() {

    companion object {
        fun newInstance(id: String) =
            DetailsChallengesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ID_USER, id)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailsChallengesViewModel: DetailsChallengesViewModel by viewModels {
        viewModelFactory
    }

    private var idUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            idUser = it?.getString(ID_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(detailsChallengesViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            detailsChallengeObserver(this)
            idUser?.let { this.getChallengeDetails(it) }
        }

        return inflater.inflate(R.layout.details_challenges_fragment, container, false)
    }

    private fun detailsChallengeObserver(detailsChallengesViewModel: DetailsChallengesViewModel) {
        detailsChallengesViewModel.detailsChallengeLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when(it.status){
                    ViewData.Status.LOADING ->  {
                        progress_bar_details_challenge_fragment.visibility = View.VISIBLE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_details_challenge_fragment.visibility = View.GONE
                        createScreenDetails(it.data)
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_details_challenge_fragment.visibility = View.GONE
                    }


                }
            }
        )
    }

    private fun createScreenDetails(data: Challenge?) {
        text_view_name_details_challenge.text = data?.name
        text_view_category_details_challenge.text = data?.category
        text_view_date_details_challenge.text = data?.approvedAt
        text_view_url_details_challenge.text = data?.url
        text_view_created_details_challenge.text = data?.createdAt
    }


}