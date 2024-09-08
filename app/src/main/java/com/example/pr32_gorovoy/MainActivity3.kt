package com.example.pr32_gorovoy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.sql.Time

class MainActivity3 : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: AppCompatButton
    private lateinit var znachenie_polzunka: TextView
    private lateinit var polzynok: SeekBar
    private lateinit var enter_metres: TextView
    private lateinit var result1: TextView
    private lateinit var button2: AppCompatButton
    private lateinit var button3: AppCompatButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button_result)
        znachenie_polzunka = findViewById(R.id.znachenie_polzynka)
        polzynok = findViewById(R.id.polzynok)
        enter_metres = findViewById(R.id.meters1)
        result1 = findViewById(R.id.result1)
        button2 = findViewById(R.id.button_next)
        button3 = findViewById(R.id.button_back)

        button2.visibility = View.INVISIBLE
        button3.visibility = View.VISIBLE

        val items = listOf("1-о комнатная квартира","2-х комнатная квартира","3-x комнатная квартира","Студия")

        val adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        polzynok.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                znachenie_polzunka.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        button.setOnClickListener {

            if (enter_metres.text.toString().isEmpty())
            {
                Toast.makeText(this, "Необходимо ввести количество метров", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (proverka_na_bukvi(enter_metres.text.toString()))
                {
                    Toast.makeText(this, "Необходимо ввести значение в цифровом формате", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    if (polzynok.progress == 0)
                    {
                        Toast.makeText(this, "Значение стоимости метров не может быть нулем", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        val text = enter_metres.text.toString()
                        val meters = text.toInt()
                        val res = when (spinner.selectedItemPosition) {
                            0 -> (polzynok.progress * meters * 1.4).toDouble()
                            1 -> (polzynok.progress * meters).toDouble()
                            2 -> (polzynok.progress * meters * 0.8).toDouble()
                            3 -> (polzynok.progress * meters * 1.1).toDouble()
                            else -> 0
                        }
                        result1.text = String.format("%.1f тыс. рублей", res)
                        button2.visibility = View.VISIBLE
                        button3.visibility = View.INVISIBLE

                    }
                }
            }
        }


    }

    fun intent1_back(view: View) {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }

    fun proverka_na_bukvi(text: String): Boolean {
        return text.any { it.isLetter() }
    }

    fun intent2_next(view: View) {
        val intent = Intent(this,MainActivity4::class.java)
        intent.putExtra("meters", enter_metres.text.toString())
        intent.putExtra("result", result1.text.toString())
        startActivity(intent)
    }

}