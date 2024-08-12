package com.example.myquizapps.view

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myquizapps.R
import com.example.myquizapps.model.Question
import com.example.myquizapps.utils.Resource
import com.example.myquizapps.utils.TokenManager
import com.example.viewmodel.quizViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var  progressDialog: ProgressDialog

    private lateinit var quiz_ViewModel: quizViewModel

    private var quiz_List: ArrayList<Question> = ArrayList()

    private lateinit var textQuestion:TextView

    private lateinit var textscore:TextView

    private lateinit var textAnswers1:TextView

    private lateinit var textAnswers2:TextView

    private lateinit var textAnswers3:TextView

    private lateinit var textAnswers4:TextView

    private lateinit var textQuizCount:TextView

    private lateinit var textCountTime:TextView

   private var selectedValue: Int = 0

   private var countScore: Int = 0

    private lateinit var materialButtonExitNo: MaterialButton

    private lateinit var materialButtonExitYes: MaterialButton

    private lateinit var materialButtonCongr: MaterialButton

    private lateinit var textViewTotalpoint: TextView

    @Inject
    lateinit var tokenManager: TokenManager

   private lateinit var timer: CountDownTimer

   private   var timerCountStatus: Boolean =false

    private lateinit var progressBar: ProgressBar

    private lateinit var imageBack_home:ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait...")

        textQuestion = findViewById(R.id.text_question)

        textscore = findViewById(R.id.text_quizScore)

        textAnswers1 = findViewById(R.id.text_quizOption1)

        textAnswers2 = findViewById(R.id.text_quizOption2)

        textAnswers3 = findViewById(R.id.text_quizOption3)

        textAnswers4 = findViewById(R.id.text_quizOption4)

        textQuizCount = findViewById(R.id.text_quizCount)

        textCountTime = findViewById(R.id.text_timeCount)

        progressBar = findViewById(R.id.simpleProgressBar)

        imageBack_home =findViewById(R.id.imageView_goback)

        quiz_ViewModel= ViewModelProvider(this)[quizViewModel::class.java]



        imageBack_home.setOnClickListener {

            val intent= Intent(this,StartActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    override fun onResume() {
        super.onResume()

        quiz_ViewModel.quizListResponseLiveData.observe(this, Observer {

            when(it){
                is Resource.Loading -> {
                    progressDialog.show()

                }

                is Resource.Error -> {

                    apiError(it.message.toString())
                    progressDialog.dismiss()
                }

                is  Resource.Success -> {

                    if(it.data!!.isEmpty()){

                        progressDialog.dismiss()

                    }else{

                        progressDialog.dismiss()

                        quiz_List.addAll(it.data)

                    }
                }
            }
        })

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this).create()
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.exit_dialog, null)

        materialButtonExitYes = dialogView.findViewById(R.id.btn_YesExitQuiz)
        materialButtonExitYes.setOnClickListener {

             finish()
            builder.dismiss()
        }

        materialButtonExitNo = dialogView.findViewById(R.id.btn_NoExitQuiz)
        materialButtonExitNo.setOnClickListener {

            builder.dismiss()
        }
        builder.setView(dialogView)
        builder.setCancelable(false)
        builder.show()
    }

    private fun apiError(it:String){
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    fun next_Quiz(view: View) {

        if (timerCountStatus==true){

            timer.cancel()

        }

        showQuiz()
    }

    override fun onStop() {
        super.onStop()

        if (timerCountStatus==true){

            timer.cancel()

        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showQuiz(){

        countTimer()

        if (selectedValue < quiz_List.size - 1){

            textQuestion.setText(""+quiz_List.get(selectedValue).question.toString())

            textscore.setText(""+quiz_List.get(selectedValue).score.toString())

            textAnswers1.setText(""+quiz_List.get(selectedValue).answers.A)

            textAnswers2.setText(""+quiz_List.get(selectedValue).answers.B)

            textAnswers3.setText(""+quiz_List.get(selectedValue).answers.C)

            textAnswers4.setText(""+quiz_List.get(selectedValue).answers.D)


            textAnswers1.setOnClickListener {


                if(quiz_List.get(selectedValue-1).correctAnswer.equals("A") && quiz_List.get(selectedValue-1).answers.A.equals(textAnswers1.text.toString().trim())){

                    textAnswers1.setBackgroundResource(R.drawable.correct_shap)

                    countScore =countScore+ quiz_List.get(selectedValue-1).score
                   tokenManager.saveQuizScore(countScore)
                }else{

                    textAnswers1.setBackgroundResource(R.drawable.error_shap)
                }
            }

            textAnswers2.setOnClickListener {

                if(quiz_List.get(selectedValue-1).correctAnswer.equals("B") && quiz_List.get(selectedValue-1).answers.B.equals(textAnswers2.text.toString().trim())){

                    textAnswers2.setBackgroundResource(R.drawable.correct_shap)

                    countScore = countScore+quiz_List.get(selectedValue-1).score

                    tokenManager.saveQuizScore(countScore)

                } else{

                    textAnswers2.setBackgroundResource(R.drawable.error_shap)
                }
            }

            textAnswers3.setOnClickListener {

                if(quiz_List.get(selectedValue-1).correctAnswer.equals("C") && quiz_List.get(selectedValue-1).answers.C.equals(textAnswers3.text.toString().trim())){

                    textAnswers3.setBackgroundResource(R.drawable.correct_shap)

                    countScore = countScore+quiz_List.get(selectedValue-1).score

                    tokenManager.saveQuizScore(countScore)
                }else{

                    textAnswers3.setBackgroundResource(R.drawable.error_shap)
                }
            }

            textAnswers4.setOnClickListener {


                if(quiz_List.get(selectedValue-1).correctAnswer.equals("D") && quiz_List.get(selectedValue-1).answers.D.equals(textAnswers4.text.toString().trim())){

                    textAnswers4.setBackgroundResource(R.drawable.correct_shap)

                    countScore = countScore+quiz_List.get(selectedValue-1).score

                    tokenManager.saveQuizScore(countScore)
                }else{

                    textAnswers4.setBackgroundResource(R.drawable.error_shap)
                }
            }
            

            selectedValue++
            textQuizCount.setText("Question :"+ selectedValue+"/ ${quiz_List.size}")


            if((quiz_List.size-1).equals(selectedValue)){

                val builder = AlertDialog.Builder(this).create()
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.congratulations_dialog, null)

                materialButtonCongr = dialogView.findViewById(R.id.btn_YesConQuiz)
                textViewTotalpoint = dialogView.findViewById(R.id.text_totalScore)

                textViewTotalpoint.setText("You have complete your Quiz. Correct answer 8/10 and you earn ${tokenManager.getQuizScore()} coin")
                materialButtonCongr.setOnClickListener {

                    val intent= Intent(this,StartActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                builder.setView(dialogView)
                builder.setCancelable(false)
                builder.show()
            }

            if(selectedValue >1){

                textAnswers1.setBackgroundResource(R.drawable.shap_color)
                textAnswers2.setBackgroundResource(R.drawable.shap_color)
                textAnswers3.setBackgroundResource(R.drawable.shap_color)
                textAnswers4.setBackgroundResource(R.drawable.shap_color)
            }

        } else {
            selectedValue = 0
        }
    }


    private fun countTimer(){

        var i = 0
        timer = object: CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                textCountTime.setText("sec: " + millisUntilFinished / 1000)

                timerCountStatus =true
                i++
               progressBar.setProgress(i * 100 / (11000 / 1000))

            }

            override fun onFinish() {

                showQuiz()

            }
        }
        timer.start()

    }
}