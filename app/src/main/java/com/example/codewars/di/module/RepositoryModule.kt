package com.example.codewars.di.module

import com.example.codewars.data.repository.challenges.authoredChallenge.AuthoredChallengeRepository
import com.example.codewars.data.repository.challenges.authoredChallenge.AuthoredChallengeService
import com.example.codewars.data.repository.challenges.completedChallenges.ChallengesRepository
import com.example.codewars.data.repository.challenges.completedChallenges.ChallengesService
import com.example.codewars.data.repository.challenges.detailsChallenge.DetailsRepository
import com.example.codewars.data.repository.challenges.detailsChallenge.DetailsService
import com.example.codewars.data.repository.user.UserRepository
import com.example.codewars.data.repository.user.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userService: UserService
    ) = UserRepository(userService)

    @Provides
    @Singleton
    fun provideChallengesRepository(
        challengesService: ChallengesService
    ) =
        ChallengesRepository(
            challengesService
        )

    @Provides
    @Singleton
    fun provideAuthoredChallengesRepository(
        authoredChallengeService: AuthoredChallengeService
    ) =
        AuthoredChallengeRepository(
            authoredChallengeService
        )

    @Provides
    @Singleton
    fun provideDetailsChallengesRepository(
        detailsService: DetailsService
    ) =
        DetailsRepository(
            detailsService
        )
}