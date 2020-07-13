package com.example.codewars.ui.Challenges.AuthoredChallenges

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.codewars.data.model.AuthoredChallenge
import com.example.codewars.data.model.AuthoredChallengeData
import com.example.codewars.data.repository.challenges.authoredChallenge.AuthoredChallengeRepository
import com.example.codewars.util.MutableSingleLiveData
import com.example.codewars.util.ViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthoredChallengesViewModel @Inject constructor (
    private val authoredChallengeRepository: AuthoredChallengeRepository)
    : ViewModel(), LifecycleObserver {

    private val disposable = CompositeDisposable()

    val authoredChallengeLiveData : MutableSingleLiveData<ViewData<AuthoredChallenge>> =
        MutableSingleLiveData()

    fun getAuthoredChallenge(name: String?){
        disposable.add(
            authoredChallengeRepository
                .getAuthoredChallenge(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    authoredChallengeLiveData.postValue(ViewData(ViewData.Status.LOADING))
                }
                .subscribe (
                    {
                        authoredChallengeLiveData.value = ViewData(ViewData.Status.SUCCESS, data = it)
                    },
                    {
                        authoredChallengeLiveData.value = ViewData(ViewData.Status.ERROR, error = it)
                    })
        )
    }
}