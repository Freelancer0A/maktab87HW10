package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


open class MainActivity : AppCompatActivity() {

    private var userName = ""
    private var password = ""
    private var intentUserName = ""
    private var intentPassword = ""

    private lateinit var resultActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        resultActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    intentUserName = it.data?.extras?.getString(SignUp.keyUserName) ?: ""
                    intentPassword = it.data?.extras?.getString(SignUp.keyPassword) ?: ""
                    findViewById<EditText>(R.id.input_username).setText(intentUserName)
                    findViewById<EditText>(R.id.input_password).setText(intentPassword)
                }
            }
    }

    fun onClick(view: View) {
        val parent: ConstraintLayout = findViewById(R.id.parent_layout)
        userName = findViewById<EditText>(R.id.input_username).text.toString()
        password = findViewById<EditText>(R.id.input_password).text.toString()
        when (view.id) {
            R.id.btn_login -> {
                if (userName == intentUserName && password == intentPassword && userName.trim() != "" && password.trim() != "") {
                    val snackBar = Snackbar
                        .make(parent, "Welcome to our community", Snackbar.LENGTH_LONG)
                    snackBar.show()
                } else if (userName.trim() == "" && password.trim() == "") {
                    Toast.makeText(baseContext, "userName & password is empty", Toast.LENGTH_SHORT)
                        .show()
                } else if (userName.trim() == "") {
                    Toast.makeText(baseContext, "userName is empty", Toast.LENGTH_SHORT).show()
                } else if (password.trim() == "") {
                    Toast.makeText(baseContext, "password is empty", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Login information is not match with signUp information.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            R.id.btn_signup -> {
                resultActivityLauncher.launch(SignUp.newInstance(this, userName, password))
            }
        }
    }
}