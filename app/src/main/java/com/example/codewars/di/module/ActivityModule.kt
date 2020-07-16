package com.example.codewars.di.module

import com.example.codewars.ui.Challenges.ChallengesActivity
import com.example.codewars.ui.User.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeUserActivity(): UserActivity

    @ContributesAndroidInjector
    abstract fun contributeChallengesActivity(): ChallengesActivity
}