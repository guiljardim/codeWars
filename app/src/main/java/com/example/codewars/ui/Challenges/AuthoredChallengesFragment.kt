package com.example.codewars.ui.Challenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.codewars.R
import javax.inject.Inject

class AuthoredChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = AuthoredChallengesFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authoredChallengesViewModel: AuthoredChallengesViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authored_challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}