package com.example.acalculator

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private var lastCal = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "OnCreate ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()

        button_0.setOnClickListener{
            Log.i(TAG, "Click no botão 0")
            if(text_visor.text == "0"){
                text_visor.text = "0"
            }else{
                text_visor.append("0")
            }
        }
        button_1.setOnClickListener{
            Log.i(TAG, "Click no botão 1")
            if(text_visor.text == "0"){
                text_visor.text = "1"
            }else{
                text_visor.append("1")
            }
        }
        button_2.setOnClickListener{
            Log.i(TAG, "Click no botão 2")
            if(text_visor.text == "0"){
                text_visor.text = "2"
            }else{
                text_visor.append("2")
            }
        }
        button_3.setOnClickListener{
            Log.i(TAG, "Click no botão 3")
            if(text_visor.text == "0"){
                text_visor.text = "3"
            }else{
                text_visor.append("3")
            }
        }
        button_4.setOnClickListener{
            Log.i(TAG, "Click no botão 4")
            if(text_visor.text == "0"){
                text_visor.text = "4"
            }else{
                text_visor.append("4")
            }
        }
        button_5.setOnClickListener{
            Log.i(TAG, "Click no botão 5")
            if(text_visor.text == "0"){
                text_visor.text = "5"
            }else{
                text_visor.append("5")
            }
        }
        button_6.setOnClickListener{
            Log.i(TAG, "Click no botão 6")
            if(text_visor.text == "0"){
                text_visor.text = "6"
            }else{
                text_visor.append("6")
            }
        }
        button_7.setOnClickListener{
            Log.i(TAG, "Click no botão 7")
            if(text_visor.text == "0"){
                text_visor.text = "7"
            }else{
                text_visor.append("7")
            }
        }
        button_8.setOnClickListener{
            Log.i(TAG, "Click no botão 8")
            if(text_visor.text == "0"){
                text_visor.text = "8"
            }else{
                text_visor.append("8")
            }
        }
        button_9.setOnClickListener{
            Log.i(TAG, "Click no botão 9")
            if(text_visor.text == "0"){
                text_visor.text = "9"
            }else{
                text_visor.append("9")
            }
        }

        button_dot.setOnClickListener{
            Log.i(TAG, "Click no botão .")
            text_visor.append(".")
        }
        button_adition.setOnClickListener{
            Log.i(TAG, "Click no botão +")
            text_visor.append("+")
        }
        button_less.setOnClickListener{
            Log.i(TAG, "Click no botão -")
            text_visor.append("-")
        }
        button_multiply.setOnClickListener{
            Log.i(TAG, "Click no botão *")
            text_visor.append("*")
        }
        button_divide.setOnClickListener{
            Log.i(TAG, "Click no botão /")
            text_visor.append("/")
        }

        button_C.setOnClickListener{
            Log.i(TAG, "Click no botão C")
            text_visor.text = ""
        }
        button_back.setOnClickListener{
            Log.i(TAG, "Click no botão /")
            text_visor.text = text_visor.text.dropLast(1)
        }
        button_H.setOnClickListener{
            if(!(lastCal == "")){
                text_visor.text = lastCal;
            }
        }

        button_equals.setOnClickListener{
            lastCal = ""
            Log.i(TAG, "Click no botão =")
            lastCal += text_visor.text.toString()
            val expression = ExpressionBuilder(text_visor.text.toString()).build()
            text_visor.text = expression.evaluate().toString()
            Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        }


    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }

}