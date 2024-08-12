package com.example.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquizapps.model.Question
import com.example.myquizapps.repository.quizRepository
import com.example.myquizapps.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class quizViewModel @Inject constructor(private val quizRepository: quizRepository) : ViewModel(){


    private  var _quizMutableResponse = MutableLiveData<Resource<List<Question>>>(Resource.Loading())
    val quizListResponseLiveData: LiveData<Resource<List<Question>>> = _quizMutableResponse


    init {

        getQustionList()
    }


     fun getQustionList(){

           viewModelScope.launch {

               val  response =  quizRepository.qustionList()

               try {

                   if(response.questions!= null){

                       _quizMutableResponse.postValue(Resource.Success(response.questions))

                   }else{

                       _quizMutableResponse.postValue(Resource.Error("Something Went Wrong"))
                   }

               } catch(e: Exception) {

                   _quizMutableResponse.postValue(Resource.Error(e.message!!))

               }

           }

        }



}




