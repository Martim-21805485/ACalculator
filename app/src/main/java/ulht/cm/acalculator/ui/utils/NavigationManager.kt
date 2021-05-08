package ulht.cm.acalculator.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ulht.cm.acalculator.R
import ulht.cm.acalculator.ui.calculator.CalculatorFragment
import ulht.cm.acalculator.ui.history.HistoryFragment
import ulht.cm.acalculator.ui.login.LoginFragment


class NavigationManager {

    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToCalculatorFragment(fm: FragmentManager){
            placeFragment(fm, CalculatorFragment())

        }

        fun goToHistoryFragment(fm: FragmentManager){
            placeFragment(fm, HistoryFragment())
        }

        fun goToLoginFragment(fm: FragmentManager){
            placeFragment(fm, LoginFragment())
        }
    }

}