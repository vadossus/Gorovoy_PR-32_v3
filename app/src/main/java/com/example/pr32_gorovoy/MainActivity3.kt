package com.example.pr32_gorovoy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.sql.Time
import java.util.logging.Handler

class MainActivity3 : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: AppCompatButton
    private lateinit var enter_metres: TextView
    private lateinit var result1: TextView
    private lateinit var button3: AppCompatButton
    private lateinit var item: TextView

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button_result)
        enter_metres = findViewById(R.id.meters1)
        result1 = findViewById(R.id.result1)
        button3 = findViewById(R.id.button_back)
        item = findViewById(R.id.selected_item)


        val items = listOf("1-о комнатная квартира","2-х комнатная квартира","3-x комнатная квартира","Студия")

        val adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val text = "1. 1-о комнатная квартира\n2. 2-x комнатная квартира\n3. 3-x комнатная квартира\n4. Студия"
        (spinner.selectedView as? TextView)?.text = text

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                item.text = selectedItem

                (spinner.selectedView as? TextView)?.text = text
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


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
                    val text = enter_metres.text.toString()
                    val meters = text.toDouble()
                    val chena_za_metr = 1000
                    val res = when (spinner.selectedItemPosition) {
                        0 -> {
                            when {
                                meters <  18 -> {
                                    Toast.makeText(this, "Метров в 1-о комнатной квартире не может быть меньше 18 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0
                                }
                                meters > 40 -> {
                                    Toast.makeText(this,"Метров в 1-о комнатной квартире не может быть больше 40 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0
                                }
                                else -> {
                                    meters * chena_za_metr * 1.4
                                }
                            }
                        }
                        1 -> {
                            when {
                                meters <  11 -> {
                                    Toast.makeText(this, "Метров в 2-x комнатной комнате не может быть меньше 11 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0

                                }
                                meters > 151 -> {
                                    Toast.makeText(this,"Метров в 2-x комнатной комнате не может быть больше 151 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0
                                }
                                else -> {
                                    meters * chena_za_metr
                                }
                            }
                        }
                        2 -> {
                            when {
                                meters <  10 -> {
                                    Toast.makeText(this, "Метров в 3-x комнатной комнате не может быть меньше 10 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0

                                }
                                meters > 208 -> {
                                    Toast.makeText(this,"Метров в 3-x комнатной комнате не может быть больше 208 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0
                                }
                                else -> {
                                    meters * chena_za_metr * 0.8
                                }
                            }
                        }
                        3 -> {
                            when {
                                meters <  18 -> {
                                    Toast.makeText(this, "Метров в студии не может быть меньше 18 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0

                                }
                                meters > 40 -> {
                                    Toast.makeText(this,"Метров в студии не может быть больше 40 кв.м", Toast.LENGTH_SHORT).show()
                                    0.0
                                }
                                else -> {
                                    meters * chena_za_metr * 1.1
                                }
                            }
                        }
                        else -> {
                            0.0
                        }
                }
                    if (res > 0.0)
                    {
                        result1.text = "${res / 1000.0} тыс. рублей"
                        android.os.Handler().postDelayed({
                            val intent = Intent(this, MainActivity4::class.java)
                            intent.putExtra("meters", enter_metres.text.toString())
                            intent.putExtra("result", result1.text.toString())
                            startActivity(intent)
                        }, 2000)
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

}