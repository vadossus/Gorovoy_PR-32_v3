package com.example.pr32_gorovoy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity2 : AppCompatActivity() {

    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var button: Button
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        button = findViewById(R.id.enter)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val save_log = sharedPreferences.getString("login", "")
        val save_pass = sharedPreferences.getString("password", "")

        login.setText(save_log)
        password.setText(save_pass)
    }

    fun intent1(view: View) {
        val loginText = login.text.toString()
        val passwordText = password.text.toString()

        if (!loginText.isNullOrEmpty() || !passwordText.isNullOrEmpty()) {
            if (loginText == "ects" && passwordText == "ects2023") {

                val editor = sharedPreferences.edit()
                editor.putString("login", loginText)
                editor.putString("password", passwordText)
                editor.apply()

                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        } else {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Ошибка")
                .setMessage("Введите поля")
                .setPositiveButton("ОК", null)
                .create()
                .show()
        }
    }
}