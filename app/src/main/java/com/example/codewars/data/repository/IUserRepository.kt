package com.example.codewars.data.repository

import com.example.codewars.data.model.User
import io.reactivex.Observable

interface IUserRepository{

    fun getUser(name: String): Observable<User>

}