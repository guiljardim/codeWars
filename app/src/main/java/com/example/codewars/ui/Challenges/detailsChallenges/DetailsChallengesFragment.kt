package com.example.codewars.ui.Challenges.detailsChallenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codewars.R
import com.example.codewars.data.model.Challenge
import com.example.codewars.util.Constants.ID_USER
import com.example.codewars.util.ViewData
import com.example.codewars.util.goneView
import com.example.codewars.util.visibilityView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.details_challenges_fragment.*
import javax.inject.Inject

class DetailsChallengesFragment : DaggerFragment() {

    companion object {
        fun newInstance(id: String?) =
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
                        progress_bar_details_challenge_fragment.visibilityView()
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_details_challenge_fragment.goneView()
                        startViews()
                        createScreenDetails(it.data)
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_details_challenge_fragment.goneView()
                    }

                }
            }
        )
    }

    private fun startViews() {
        text_view_description_details_challenge_title.visibilityView()
        text_view_category_details_challenge_title.visibilityView()
        text_view_date_details_challenge_title.visibilityView()
        text_view_details_title.visibilityView()
        view_category_details.visibilityView()
        view_date_details.visibilityView()
        view_description_details.visibilityView()
    }

    private fun createScreenDetails(data: Challenge?) {
        text_view_name_details_challenge.text = data?.name
        text_view_date_details_challenge.text = data?.createdAt?.split("T")?.get(0) ?: ""
        text_view_category_details_challenge.text = data?.category
        text_view_description_details_challenge.text = data?.description
    }


}