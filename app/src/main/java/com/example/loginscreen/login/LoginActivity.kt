package com.example.loginscreen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.loginscreen.NewActivity
import com.example.loginscreen.R
import com.example.loginscreen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener {

            if (binding.emailField.text.toString() == "a") {
                if (binding.passwordField.text.toString() == "a") {

                    val intent = Intent(this,NewActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Pass Field!", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(this, "Email Field!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}