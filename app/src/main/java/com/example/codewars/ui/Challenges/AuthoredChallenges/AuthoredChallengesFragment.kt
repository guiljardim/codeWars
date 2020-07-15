package com.example.codewars.ui.Challenges.AuthoredChallenges

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codewars.R
import com.example.codewars.data.model.AuthoredChallengeData
import com.example.codewars.ui.Challenges.OnFragmentChallengesInteractionListener
import com.example.codewars.util.Constants.NAME_USER
import com.example.codewars.util.ViewData
import com.example.codewars.util.visibilityView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.authored_challenges_fragment.*
import javax.inject.Inject

class AuthoredChallengesFragment : DaggerFragment(), AuthoredChallengeAdapter.OnItemClickListener {

    companion object {
        fun newInstance(user: String?) =
            AuthoredChallengesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(NAME_USER, user)
                }
            }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var listener: OnFragmentChallengesInteractionListener? = null

    private val authoredChallengesViewModel: AuthoredChallengesViewModel by viewModels {
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
        with(authoredChallengesViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            authoredChallengeObserver(this)
            this.getAuthoredChallenge(nameUser)

        }

        return inflater.inflate(R.layout.authored_challenges_fragment, container, false)
    }

    private fun authoredChallengeObserver(authoredChallengesViewModel: AuthoredChallengesViewModel){
        authoredChallengesViewModel.authoredChallengeLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when(it.status){
                    ViewData.Status.LOADING ->  {
                        progress_bar_authored_challenge_fragment.visibility = View.VISIBLE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_authored_challenge_fragment.visibility = View.GONE
                        text_view_authored_challenge.visibilityView()
                        it.data?.let { data -> createAuthoredList(data.data) }
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_authored_challenge_fragment.visibility = View.GONE
                    }


                }
            }
        )
    }

    private fun createAuthoredList(listOfAuthoredChallenges: List<AuthoredChallengeData>){
        recycle_view_authored_challenge_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_authored_challenge_fragment.adapter =
            AuthoredChallengeAdapter(listOfAuthoredChallenges, context, this)
    }

    override fun onItemClick(idChallenge: String?) {
        listener?.goToDetailsChallengeFragment(idChallenge)
    }

}