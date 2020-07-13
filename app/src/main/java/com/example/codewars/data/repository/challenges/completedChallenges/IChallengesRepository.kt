package com.example.codewars.data.repository.challenges.completedChallenges

import com.example.codewars.data.model.CompletedChallenge
import io.reactivex.Observable

interface IChallengesRepository {
    fun getCompletedChallenge(name: String?, page: Int): Observable<CompletedChallenge>
}