package com.example.codewars.di.module

import com.example.codewars.ui.Challenges.authoredChallenges.AuthoredChallengesFragment
import com.example.codewars.ui.Challenges.completedChallenges.CompletedChallengeFragment
import com.example.codewars.ui.Challenges.detailsChallenges.DetailsChallengesFragment
import com.example.codewars.ui.User.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeChallengesFragment(): CompletedChallengeFragment

    @ContributesAndroidInjector
    abstract fun contributeAuthoredChallengesFragment(): AuthoredChallengesFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsChallengesFragment(): DetailsChallengesFragment

}