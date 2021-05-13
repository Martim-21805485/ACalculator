package ulht.cm.acalculator.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ulht.cm.acalculator.R
import ulht.cm.acalculator.ui.utils.NavigationManager

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
    }


    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_cal -> NavigationManager.goToCalculatorFragment(supportFragmentManager)
            R.id.nav_his -> NavigationManager.goToHistoryFragment(supportFragmentManager)
            R.id.nav_login -> NavigationManager.goToLoginFragment(supportFragmentManager)
            R.id.nav_registo -> NavigationManager.goToRegistoFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else if(supportFragmentManager.backStackEntryCount==1){
            finish()
        }else{
            super.onBackPressed()
        }
    }
}