package ulht.cm.acalculator.data.remote.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ulht.cm.acalculator.data.remote.requests.Registo
import ulht.cm.acalculator.data.remote.responses.RegistoResponse

interface RegistoService {

    @POST("users/register")
    suspend fun registo(@Body body: Registo): Response<RegistoResponse>
}