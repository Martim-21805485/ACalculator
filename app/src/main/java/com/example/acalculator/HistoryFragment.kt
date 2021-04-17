package com.example.acalculator

import android.content.Context
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onStart() {
        super.onStart()
        var b = Bundle()
        var list = b.getParcelableArray(EXTRA_HISTORY)


        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter = HistoryAdapter(activity as Context,R.layout.item_expression, mutableListOf(Operation("colocar bundle",12.0)))
    }

}