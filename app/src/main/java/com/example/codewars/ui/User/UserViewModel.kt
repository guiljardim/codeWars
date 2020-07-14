package com.example.codewars.ui.User

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.codewars.data.model.User
import com.example.codewars.data.repository.user.UserRepository
import com.example.codewars.util.MutableSingleLiveData
import com.example.codewars.util.ViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(), LifecycleObserver {

    private val disposable = CompositeDisposable()

   val userLiveData: MutableSingleLiveData<ViewData<List<User>>> = MutableSingleLiveData()

    private val listOfUserToShow: MutableList<User> = mutableListOf()

    fun getUser(name: String){
        disposable.add(
            userRepository.getUser(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    userLiveData.postValue(ViewData(ViewData.Status.LOADING))
                }
                .subscribe (
                    {
                        if(listOfUserToShow.contains(it)){
                            userLiveData.value = ViewData(ViewData.Status.SUCCESS, data = listOfUserToShow)
                        }else{
                            listOfUserToShow.add(it)
                            userLiveData.value = ViewData(ViewData.Status.SUCCESS, data = listOfUserToShow)
                        }
                    },
                    {
                        userLiveData.value = ViewData(ViewData.Status.ERROR, error = it)
                    })
        )
    }
}
