package com.example.codewars.ui.User

interface OnFragmentInteractionListener {
    fun goToUserFragment()
    fun gotToChallengesActivity(user: String, fragment: UserFragment)
}