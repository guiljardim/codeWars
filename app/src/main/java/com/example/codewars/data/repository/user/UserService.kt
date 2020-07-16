package com.example.codewars.data.repository.user

import com.example.codewars.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{name}")
    fun getUser(@Path("name") name: String): Observable<User>

}