package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SignUp : AppCompatActivity() {
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText

    companion object {
        const val keyUserName: String = "username"
        const val keyPassword: String = "password"
        var intentUserName = ""
        var intentPassword = ""

        fun newInstance(context: Context, username: String, password: String): Intent = Intent(
            context,
            SignUp::class.java
        ).apply {
            putExtra(keyUserName, username)
            putExtra(keyPassword, password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etUserName = findViewById(R.id.input_username)
        etPassword = findViewById(R.id.input_password)
        intentUserName = intent.getStringExtra(keyUserName).toString()
        intentPassword = intent.getStringExtra(keyPassword).toString()
        etUserName.setText(intentUserName)
        etPassword.setText(intentPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUpSecond)
        btnSignUp.setOnClickListener {
            passData()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        passData()
        super.onBackPressed()
    }

    private fun passData() {
        val username = etUserName.text.toString()
        val password = etPassword.text.toString()
        if (username == "" || password == "") Toast.makeText(
            this,
            "Please type your information",
            Toast.LENGTH_SHORT
        ).show()
        else {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            intent.putExtra(keyUserName, username)
            intent.putExtra(keyPassword, password)
            finish()
        }
    }
}