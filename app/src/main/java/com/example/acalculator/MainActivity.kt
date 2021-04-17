package com.example.acalculator

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*
import kotlin.collections.ArrayList

const val EXTRA_HISTORY = "com.example.acalculator.HISTORY"
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
    }

    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_open)
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_cal -> NavigationManager.goToCalculatorFragment(supportFragmentManager)
            R.id.nav_his -> NavigationManager.goToHistoryFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true


    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
                else if (supportFragmentManager.backStackEntryCount == 1) finish()
                else super.onBackPressed()
    }




}