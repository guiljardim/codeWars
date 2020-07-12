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
import com.example.codewars.ui.User.UserViewModel
import com.example.codewars.util.Constants.NAME_USER
import javax.inject.Inject


class ChallengesFragment : Fragment() {

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
        return inflater.inflate(R.layout.challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}