package ulht.cm.acalculator.ui.login

import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.domain.auth.AuthLogic
import ulht.cm.acalculator.ui.callback.login

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel(): ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))


     fun onClickButtonLogin(callBack: login,email: String, password: String){
        authLogic.authenticateUser(callBack,email,password)
    }


}