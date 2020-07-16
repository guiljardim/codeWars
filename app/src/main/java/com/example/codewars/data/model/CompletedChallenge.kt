package com.example.codewars.data.model

data class CompletedChallenge(
    val totalPages: Int,
    val totalItems: Long,
    val data: List<CompletedChallengeData>
)