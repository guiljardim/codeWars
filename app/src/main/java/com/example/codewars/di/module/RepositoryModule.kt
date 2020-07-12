package com.example.codewars.di.module

import com.example.codewars.data.repository.challenges.ChallengesRepository
import com.example.codewars.data.repository.challenges.ChallengesService
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
    ) = ChallengesRepository(challengesService)
}