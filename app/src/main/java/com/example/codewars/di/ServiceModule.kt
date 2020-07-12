package com.example.codewars.di

import com.example.codewars.data.repository.UserService
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
}