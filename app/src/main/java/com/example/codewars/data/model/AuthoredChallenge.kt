package com.example.codewars.data.model

data class AuthoredChallenge(
    val id : String,
    val name : String,
    val description : String,
    val ranks : Long,
    val rankName : String,
    val tags : List<String>,
    val languages : List<String>
)