package com.example.codewars.data.model

data class AuthoredChallengeData(
    val id : String,
    val name : String,
    val description : String,
    val rank : Long,
    val rankName : String,
    val tags : List<String>,
    val languages : List<String>
)