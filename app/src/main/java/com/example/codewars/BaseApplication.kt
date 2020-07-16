package com.example.codewars
import com.example.codewars.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


open class BaseApplication: DaggerApplication(){

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val daggerApplicationComponent =
            DaggerApplicationComponent.builder()
                .application(this)
                .build()

        daggerApplicationComponent.inject(this)

        return daggerApplicationComponent
    }
}