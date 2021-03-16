package com.example.acalculator

import android.content.res.Configuration
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"
    private val list = mutableListOf("1+1=2")
    private var historyAdapter: HistoryAdapter? = null

    private var lastCal = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "OnCreate ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart ${DateFormat.format(" hh:mm:ss", Date())}", Toast.LENGTH_SHORT).show()

        button_0.setOnClickListener{ onClickSymbol("0") }
        button_1.setOnClickListener{ onClickSymbol("1") }
        button_2.setOnClickListener{ onClickSymbol("2") }
        button_3.setOnClickListener{ onClickSymbol("3") }
        button_4.setOnClickListener{ onClickSymbol("4") }
        button_5.setOnClickListener{ onClickSymbol("5") }
        button_6.setOnClickListener{ onClickSymbol("6") }
        button_7.setOnClickListener{ onClickSymbol("7") }
        button_8.setOnClickListener{ onClickSymbol("8") }
        button_9.setOnClickListener{ onClickSymbol("9") }

        button_dot.setOnClickListener{ onClickSymbol(".") }
        button_adition.setOnClickListener{ onClickSymbol("+")}
        button_less.setOnClickListener{ onClickSymbol("-")}
        button_multiply.setOnClickListener{ onClickSymbol("*")}
        button_divide.setOnClickListener{ onClickSymbol("/")}

        button_C.setOnClickListener{
            Log.i(TAG, "Click no botão C")
            text_visor.text = ""
        }
        button_back.setOnClickListener{
            Log.i(TAG, "Click no botão Back")
            text_visor.text = text_visor.text.dropLast(1)
        }
        button_H.setOnClickListener{
            if(!(lastCal == "")){
                text_visor.text = lastCal;
            }
        }

        button_equals.setOnClickListener{ onClickEquals() }
        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
        } else {
            historyAdapter = HistoryAdapter(this, R.layout.item_expression, list as ArrayList<String>)
            list_historic.adapter = historyAdapter
        }

        list_historic.setOnItemClickListener(OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, list.elementAt(position), Toast.LENGTH_SHORT).show()
        })
    }

    private fun onClickSymbol(symbol: String){
        Log.i(TAG, "Click no botão ${symbol}")
        if(text_visor.text == "0"){
            text_visor.text = symbol
        }else{
            text_visor.append(symbol)
        }
    }

    private fun onClickEquals(){
        try {
            lastCal = ""
            Log.i(TAG, "Click no botão =")
            lastCal += text_visor.text.toString()
            val expression = ExpressionBuilder(text_visor.text.toString()).build()
            text_visor.text = expression.evaluate().toString()
            lastCal += "=${text_visor.text}"
            Log.i(TAG, lastCal)
            list.add(lastCal)
            val orientation = this.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // code for portrait mode
            } else {
                historyAdapter?.notifyDataSetChanged()
            }
            list_historic.setOnItemClickListener(OnItemClickListener { adapterView, view, position, id ->
                Toast.makeText(this, list.elementAt(position), Toast.LENGTH_SHORT).show()
            })
            Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        } catch (e: IllegalArgumentException) {
            // handler
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
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