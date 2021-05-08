package ulht.cm.acalculator.ui.login

import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.domain.auth.AuthLogic

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel(): ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))

    fun onClickButtonLogin(email: String, password: String){
         authLogic.authenticateUser(email,password)
    }
}