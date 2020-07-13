package com.example.codewars.ui.Challenges.detailsChallenges

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.codewars.data.model.Challenge
import com.example.codewars.data.repository.challenges.detailsChallenge.DetailsRepository
import com.example.codewars.util.MutableSingleLiveData
import com.example.codewars.util.ViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsChallengesViewModel @Inject constructor
    (private val detailsRepository: DetailsRepository)
    : ViewModel(), LifecycleObserver {

    private val disposable = CompositeDisposable()

    val detailsChallengeLiveData: MutableSingleLiveData<ViewData<Challenge>> =
        MutableSingleLiveData()

    fun getChallengeDetails(id: String){
        disposable.add(
            detailsRepository
                .getChallengeDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    detailsChallengeLiveData.postValue(ViewData(ViewData.Status.LOADING))
                }
                .subscribe (
                    {
                        detailsChallengeLiveData.value = ViewData(ViewData.Status.SUCCESS, data = it)
                    },
                    {
                        detailsChallengeLiveData.value = ViewData(ViewData.Status.ERROR, error = it)
                    })
        )
    }
}
