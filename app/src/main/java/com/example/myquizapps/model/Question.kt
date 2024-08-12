package com.example.myquizapps.model

data class Question(
    val answers: Answers,
    val correctAnswer: String,
    val question: String,
    val questionImageUrl: String,
    val score: Int
)