package com.example.codewars.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codewars.di.ViewModelFactory
import com.example.codewars.di.ViewModelKey
import com.example.codewars.ui.Challenges.AuthoredChallengesViewModel
import com.example.codewars.ui.Challenges.completedChallenges.ChallengesViewModel
import com.example.codewars.ui.User.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChallengesViewModel::class)
    internal abstract fun challengesViewModel(challengesViewModel: ChallengesViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AuthoredChallengesViewModel::class)
    internal abstract fun authoredChallengesViewModel(authoredChallengesViewModel: AuthoredChallengesViewModel): ViewModel

}