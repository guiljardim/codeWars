package com.example.codewars.data.repository

import com.example.codewars.data.model.User
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository: IUserRepository{

    @Inject
    lateinit var userService : UserService

    override fun getUser(name: String): Observable<User> = userService.getUser(name)


}