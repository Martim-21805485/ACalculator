package ulht.cm.acalculator.ui.registo

import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.domain.auth.AuthLogic
import ulht.cm.acalculator.ui.callback.login
import ulht.cm.acalculator.ui.callback.registo

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class RegistoViewModel(): ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))


    fun onClickButtonRegisto(callBack: registo,email: String, password: String, nome: String){
        authLogic.registoUser(callBack,email,password,nome)
    }


}