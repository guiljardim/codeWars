package com.example.codewars.di.module

import com.example.codewars.data.repository.challenges.ChallengesService
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
}