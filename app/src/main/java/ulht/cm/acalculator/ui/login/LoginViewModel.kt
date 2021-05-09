package ulht.cm.acalculator.ui.login

import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.domain.auth.AuthLogic
import ulht.cm.acalculator.ui.listeners.OnListChanged
import ulht.cm.acalculator.ui.listeners.OnLoginTrue

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel(): ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    private var listener: OnLoginTrue? = null

     fun onClickButtonLogin(email: String, password: String){
        authLogic.authenticateUser(email,password)
    }

    private fun notifyOnLogin(){
        listener?.onLoginTrue()
    }

    fun registerListener(listener: OnLoginTrue){
        this.listener = listener
    }

    fun unregisterListener(){
        listener = null
    }
}