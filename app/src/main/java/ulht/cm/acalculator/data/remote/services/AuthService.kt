package ulht.cm.acalculator.data.remote.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ulht.cm.acalculator.data.remote.requests.Login
import ulht.cm.acalculator.data.remote.responses.LoginResponse

interface AuthService {

    @POST("users/login")
    suspend fun login(@Body body: Login): Response<LoginResponse>
}