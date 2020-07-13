package com.example.codewars.data.repository.challenges.authoredChallenge

import com.example.codewars.data.model.AuthoredChallenge
import io.reactivex.Observable

interface IAuthoredChallengeRepository {

    fun getAuthoredChallenge(name: String?): Observable<List<AuthoredChallenge>>
}