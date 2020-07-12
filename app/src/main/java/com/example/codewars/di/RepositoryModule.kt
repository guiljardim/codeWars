package com.example.codewars.di

import com.example.codewars.data.repository.UserRepository
import com.example.codewars.data.repository.UserService
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
}