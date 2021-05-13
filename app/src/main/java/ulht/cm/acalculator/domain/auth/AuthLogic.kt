package ulht.cm.acalculator.domain.auth

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import ulht.cm.acalculator.data.remote.requests.Login
import ulht.cm.acalculator.data.remote.requests.Registo
import ulht.cm.acalculator.data.remote.responses.LoginResponse
import ulht.cm.acalculator.data.remote.services.AuthService
import ulht.cm.acalculator.data.remote.services.RegistoService
import ulht.cm.acalculator.ui.callback.login
import ulht.cm.acalculator.ui.callback.registo


class AuthLogic (private val retrofit: Retrofit) {

    private val TAG = AuthLogic::class.java.simpleName

     fun authenticateUser(callback: login,email: String, password: String) {
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.login(Login(email,password))
            if(response.isSuccessful){
                response.body()?.token?.let { callback.onLoginTrue(it) }
            }
        }
    }

    fun registoUser(callback: registo,email: String, password: String, name: String) {
        val service = retrofit.create(RegistoService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.registo(Registo(name,email,password))
            if(response.isSuccessful){
                callback.onRegisto()
            }
        }
    }
}