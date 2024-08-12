package com.example.myquizapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myquizapps.model.Question
import com.example.myquizapps.retrofit.ApiService
import com.example.myquizapps.utils.Resource
import retrofit2.Response
import javax.inject.Inject

public  class  quizRepository@Inject constructor(private val apiService: ApiService) {


   suspend fun qustionList() = apiService.getPost()


}
