package com.example.codewars.data.repository.user

import com.example.codewars.data.model.User
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
): IUserRepository {

    override fun getUser(name: String): Observable<User> = userService.getUser(name)


}