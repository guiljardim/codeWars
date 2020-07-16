package com.example.codewars.data.repository.challenges.authoredChallenge

import com.example.codewars.data.model.AuthoredChallenge
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthoredChallengeService {
    @GET("users/{name}/code-challenges/authored")
    fun getAuthoredChallenges(
        @Path("name") name : String?
    ) : Observable<AuthoredChallenge>
}