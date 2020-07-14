package com.example.codewars.data.repository.challenges.detailsChallenge

import com.example.codewars.data.model.Challenge
import io.reactivex.Observable
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val detailsService: DetailsService): IDetailsRepository {

    override fun getChallengeDetails(id: String): Observable<Challenge> = detailsService.getChallengeDetails(id)



}