package com.example.codewars.data.repository.challenges

import com.example.codewars.data.model.CompletedChallenge
import io.reactivex.Observable
import javax.inject.Inject

class ChallengesRepository @Inject constructor(private val challengesService: ChallengesService):
    IChallengesRepository {

    override fun getCompletedChallenge(name: String?, page: Int):
            Observable<CompletedChallenge> =
        challengesService.getCompletedChallenges(name, page)


}