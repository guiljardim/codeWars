package com.example.codewars.data.repository.challenges.detailsChallenge

import com.example.codewars.data.model.Challenge
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET("code-challenges/{id}")
    fun getChallengeDetails(
        @Path("id") id : String
    ) : Observable<Challenge>
}