package com.example.codewars.data.repository.challenges.completedChallenges

import com.example.codewars.data.model.CompletedChallenge
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChallengesService {
    @GET("users/{name}/code-challenges/completed")
    fun getCompletedChallenges(
        @Path("name") name: String?,
        @Query("page") page: Int
    ): Observable<CompletedChallenge>

}