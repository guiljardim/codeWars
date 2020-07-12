package com.example.codewars.ui.Challenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codewars.R

class AuthoredChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = AuthoredChallengesFragment()
    }

    private lateinit var viewModel: AuthoredChallengesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authored_challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthoredChallengesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}