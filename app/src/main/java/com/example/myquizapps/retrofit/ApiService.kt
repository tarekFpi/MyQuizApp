package com.example.myquizapps.retrofit
import com.example.myquizapps.model.quiz_response
import retrofit2.http.GET



interface ApiService {

    @GET("quiz.json")
     suspend fun getPost(): quiz_response
}
