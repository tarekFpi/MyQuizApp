/*
package com.example.jetpackQuizTask.repository

import com.example.jetpackQuizTask.model.quizApiResponse.Question
import com.example.jetpackQuizTask.retrofit.ApiService
import com.example.jetpackQuizTask.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class  quizRepository@Inject constructor(private val apiService: ApiService) {

    private  var _response = MutableStateFlow<Resource<List<Question>>>(Resource.Loading())
    val stateflow_response: StateFlow<Resource<List<Question>>> = _response


    suspend  fun qustionList(){

        val  response =  apiService.getPost()

        try {

            if(response.questions!= null){

                _response.emit(Resource.Success(response.questions))

            }else{

                _response.emit(Resource.Error("Something Went Wrong"))
            }

        } catch(e: Exception) {

            _response.emit(Resource.Error(e.message!!))

        }

    }

}
*/
