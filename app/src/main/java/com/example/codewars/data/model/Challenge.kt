package com.example.codewars.data.model

data class Challenge(
    val id : String,
    val name : String,
    val category : String,
    val publishedAt : String,
    val approvedAt : String,
    val languages : List<String>,
    val url : String,
    val rank : Rank,
    val createdAt : String,
    val createdBy : UserInfo,
    val approvedBy : UserInfo,
    val description : String,
    val totalAttempts : Int,
    val totalCompleted : Int,
    val totalStars : Int,
    val voteScore : Int,
    val tags : List<String>
)