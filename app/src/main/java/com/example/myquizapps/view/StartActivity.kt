package com.example.myquizapps.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myquizapps.R
import com.example.myquizapps.utils.CheckInternetConnection
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {

    //MaterialButton
    private lateinit var materialButtonNo: MaterialButton

    private lateinit var materialButtonYes: MaterialButton

    private lateinit var materialButtonExitNo: MaterialButton

    private lateinit var materialButtonExitYes: MaterialButton

    @Inject
    lateinit var checkInternetConnection: CheckInternetConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

    }

    fun play_Quiz(view: View) {


        val builder = AlertDialog.Builder(this).create()
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.start_quiz_dialog, null)

       materialButtonYes = dialogView.findViewById(R.id.btn_YesStartQuiz)
        materialButtonYes.setOnClickListener {

            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
            builder.dismiss()
        }

        materialButtonNo = dialogView.findViewById(R.id.btn_NoStartQuiz)
        materialButtonNo.setOnClickListener {

            builder.dismiss()
        }
        builder.setView(dialogView)
        builder.setCancelable(false)
        builder.show()

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

    override fun onResume() {
        super.onResume()

        if (!checkInternetConnection.isInternetAvailable(this))

            Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

    }
}