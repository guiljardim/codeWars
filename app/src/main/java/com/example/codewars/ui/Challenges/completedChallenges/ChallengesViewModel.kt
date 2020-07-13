package com.example.codewars.ui.Challenges.completedChallenges

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.codewars.data.model.CompletedChallenge
import com.example.codewars.data.model.User
import com.example.codewars.data.repository.challenges.ChallengesRepository
import com.example.codewars.util.MutableSingleLiveData
import com.example.codewars.util.ViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChallengesViewModel @Inject constructor(
    val challengesRepository: ChallengesRepository
) : ViewModel(), LifecycleObserver {
    private val disposable = CompositeDisposable()

    val completedChallengeLiveData: MutableSingleLiveData<ViewData<CompletedChallenge>> =
        MutableSingleLiveData()

    fun getCompletedChallenge(name: String?, page: Int){
        disposable.add(
            challengesRepository
                .getCompletedChallenge(name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    completedChallengeLiveData.postValue(ViewData(ViewData.Status.LOADING))
                }
                .subscribe (
                    {
                        completedChallengeLiveData.value = ViewData(ViewData.Status.SUCCESS, data = it)
                    },
                    {
                        completedChallengeLiveData.value = ViewData(ViewData.Status.ERROR, error = it)
                    })
        )
    }
}