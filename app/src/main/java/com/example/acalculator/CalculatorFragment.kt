package com.example.acalculator

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*
import kotlin.collections.ArrayList

class CalculatorFragment : Fragment() {
    private val VISOR_KEY = "visor"
    private val list = mutableListOf(Operation("1+1",2.0))
    private var historyAdapter: HistoryAdapter? = null
    private var lastCal = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_calculator, container, false)
        ButterKnife.bind(this,view)
        return view
    }

    override fun onStart() {
        super.onStart()
        button_C.setOnClickListener{
            text_visor.text = ""
        }
        button_back.setOnClickListener{
            text_visor.text = text_visor.text.dropLast(1)
        }
        button_H.setOnClickListener{
            onClickHistory()
        }

        button_equals.setOnClickListener{ onClickEquals() }
        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
        } else {
            historyAdapter = HistoryAdapter(activity as Context, R.layout.item_expression, list as ArrayList<Operation>)
            list_historicLand.layoutManager = LinearLayoutManager(activity as Context)
            list_historicLand.adapter = historyAdapter
        }
    }

    @Optional
    @OnClick(R.id.button_0,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_adition,R.id.button_less,R.id.button_multiply,R.id.button_divide,R.id.button_dot)
    fun onClickSymbol(view: View){
        val symbol = view.tag.toString()
        if(text_visor.text == "0"){
            text_visor.text = symbol
        }else{
            text_visor.append(symbol)
        }
    }

    fun onClickHistory(){
        val intent = Intent(activity as Context , HistoryFragment::class.java)
        intent.apply { putExtra(EXTRA_HISTORY, ArrayList(list)) }
        activity?.supportFragmentManager?.let { NavigationManager.goToHistoryFragment(it) }

    }

    private fun onClickEquals(){
        try {
            lastCal = ""
            lastCal += text_visor.text.toString()
            var exp = text_visor.text.toString()
            val expression = ExpressionBuilder(text_visor.text.toString()).build()
            text_visor.text = expression.evaluate().toString()
            lastCal += "=${text_visor.text}"
            list.add(Operation(exp,expression.evaluate()))
            val orientation = this.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // code for portrait mode
            } else {
                historyAdapter?.notifyDataSetChanged()
            }
        } catch (e: IllegalArgumentException) {
            // handler
        }

    }




}