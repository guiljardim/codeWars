package com.example.codewars.di

import com.example.codewars.ui.User.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

}