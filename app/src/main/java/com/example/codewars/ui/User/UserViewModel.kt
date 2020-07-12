package com.example.codewars.ui.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codewars.data.model.User
import com.example.codewars.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val userLiveData by lazy {
        MutableLiveData<User>()
    }

    fun getUser(name: String): LiveData<User> {
        disposable.add(
                userRepository.getUser(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { userLiveData.postValue(it) }

        )
        return userLiveData
    }
}
