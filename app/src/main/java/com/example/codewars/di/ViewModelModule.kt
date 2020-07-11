package com.example.codewars.di

import androidx.lifecycle.ViewModel
import com.example.codewars.ui.User.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(userViewModel: UserViewModel): ViewModel

}