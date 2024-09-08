package com.example.pr32_gorovoy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class MainActivity4 : AppCompatActivity() {
    private lateinit var res: TextView
    private lateinit var res2: TextView
    private lateinit var button: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        res = findViewById(R.id.result2)
        res2 = findViewById(R.id.result3)
        button = findViewById(R.id.button_back2)

        val meters = intent.getStringExtra("meters")
        val result = intent.getStringExtra("result")

        res.text = meters
        res2.text = result

        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("login")
            editor.remove("password")
            editor.apply()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


    }
}