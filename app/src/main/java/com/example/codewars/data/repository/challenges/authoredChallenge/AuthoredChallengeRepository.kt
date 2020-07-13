package com.example.codewars.data.repository.challenges.authoredChallenge

import com.example.codewars.data.model.AuthoredChallenge
import io.reactivex.Observable
import javax.inject.Inject

class AuthoredChallengeRepository @Inject constructor(private val authoredChallengeService: AuthoredChallengeService):
    IAuthoredChallengeRepository {

    override fun getAuthoredChallenge(name: String?): Observable<AuthoredChallenge> =
        authoredChallengeService.getAuthoredChallenges(name)
}