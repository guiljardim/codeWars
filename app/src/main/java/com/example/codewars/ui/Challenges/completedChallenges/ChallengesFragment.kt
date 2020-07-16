package com.example.codewars.ui.Challenges.completedChallenges

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.codewars.data.model.CompletedChallenge
import com.example.codewars.data.model.CompletedChallengeData
import com.example.codewars.ui.Challenges.OnFragmentChallengesInteractionListener
import com.example.codewars.ui.Challenges.completedChallenges.CompletedChallengeAdapter.OnBottomReachedListener
import com.example.codewars.ui.Challenges.completedChallenges.CompletedChallengeAdapter.OnItemClickListener
import com.example.codewars.ui.User.OnFragmentInteractionListener
import com.example.codewars.ui.User.UserAdapter
import com.example.codewars.util.Constants.NAME_USER
import com.example.codewars.util.ViewData
import com.example.codewars.util.visibilityView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.challenges_fragment.*
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject


class ChallengesFragment : DaggerFragment(), OnItemClickListener, OnBottomReachedListener {

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

    private var listener: OnFragmentChallengesInteractionListener? = null

    private var listOfChallenges: MutableList<CompletedChallengeData> = mutableListOf()

    private lateinit var completedChallengeAdapter: CompletedChallengeAdapter

    private var page = 1

    private var totalPages: Int = 0

    private var totalItems: Int = 0

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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentChallengesInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(challengesViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            completedChallengeObserver(this)
            completedChallengeLoadMoreObserver(this)
            this.getCompletedChallenge(nameUser, 0)
        }

        return inflater.inflate(R.layout.challenges_fragment, container, false)
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
                        text_view_completed_challenge.visibility = View.VISIBLE
                        totalPages = it.data?.totalPages ?: 0
                        totalItems = it.data?.totalItems?.toInt() ?: 0
                        it.data?.data?.let { data -> listOfChallenges.addAll(data) }
                        createChallengeList()
                    }

                    ViewData.Status.ERROR -> {
                        text_view_completed_challenge_empty_state.visibilityView()
                        progress_bar_challenge_fragment.visibility = View.GONE
                    }
                }
            }
        )
    }

    private fun completedChallengeLoadMoreObserver(challengesViewModel: ChallengesViewModel){
        challengesViewModel.completedChallengePaginationLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when(it.status){
                    ViewData.Status.LOADING ->  {
                        progress_bar_challenge_fragment.visibility = View.VISIBLE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_challenge_fragment.visibility = View.GONE
                        text_view_completed_challenge.visibility = View.VISIBLE
                        it.data?.data?.let { data -> listOfChallenges.addAll(data) }
                        completedChallengeAdapter.notifyDataSetChanged()
                    }

                    ViewData.Status.ERROR -> {
                        text_view_completed_challenge_empty_state.visibilityView()
                        progress_bar_challenge_fragment.visibility = View.GONE
                    }
                }
            }
        )
    }

    private fun createChallengeList() {
        if(listOfChallenges.isEmpty()){
            text_view_completed_challenge_empty_state.visibilityView()
        }
        completedChallengeAdapter = CompletedChallengeAdapter(listOfChallenges, context, this, this, nameUser)

        recycle_view_challenge_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_challenge_fragment.adapter = completedChallengeAdapter
    }

    override fun onItemClick(idChallenge: String?) {
        listener?.goToDetailsChallengeFragment(idChallenge)
    }

    override fun onBottomReached() {
        if(listOfChallenges.size != totalItems){

            if(page <= totalPages) challengesViewModel.getCompleteChallengePagination(nameUser, page)

            page++
        } else {
            Toast.makeText(context,"you have reached the end of the list", Toast.LENGTH_LONG).show()
        }

    }



}