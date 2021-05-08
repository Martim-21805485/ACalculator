package ulht.cm.acalculator.domain.auth

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import ulht.cm.acalculator.data.remote.requests.Login
import ulht.cm.acalculator.data.remote.services.AuthService



class AuthLogic (private val retrofit: Retrofit) {

    private val TAG = AuthLogic::class.java.simpleName

    fun authenticateUser(email: String, password: String){
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.login(Login(email,password))
            if(response.isSuccessful){
                Log.i(TAG,"response.isSuccessful")
                Log.i(TAG, response.body().toString())
            }else{
                Log.i(TAG,"response.isNOTSuccessful")
            }
        }
    }
}