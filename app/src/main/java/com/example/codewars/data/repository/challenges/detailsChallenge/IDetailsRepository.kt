package com.example.codewars.data.repository.challenges.detailsChallenge

import com.example.codewars.data.model.Challenge
import io.reactivex.Observable

interface IDetailsRepository {
    fun getChallengeDetails(id: String): Observable<Challenge>
}