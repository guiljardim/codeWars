package com.example.codewars.ui.Challenges

import androidx.lifecycle.ViewModel
import com.example.codewars.data.repository.challenges.ChallengesRepository
import javax.inject.Inject

class AuthoredChallengesViewModel @Inject constructor (val challengesRepository: ChallengesRepository)
    : ViewModel() {
}