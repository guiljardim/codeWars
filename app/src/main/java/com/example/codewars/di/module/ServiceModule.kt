package com.example.codewars.di.module

import com.example.codewars.data.repository.challenges.authoredChallenge.AuthoredChallengeService
import com.example.codewars.data.repository.challenges.completedChallenges.ChallengesService
import com.example.codewars.data.repository.challenges.detailsChallenge.DetailsService
import com.example.codewars.data.repository.user.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
      return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideChallengesService(retrofit: Retrofit): ChallengesService {
        return retrofit.create(ChallengesService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthoredChallengesService(retrofit: Retrofit): AuthoredChallengeService {
        return retrofit.create(AuthoredChallengeService::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailsChallengesService(retrofit: Retrofit): DetailsService {
        return retrofit.create(DetailsService::class.java)
    }
}