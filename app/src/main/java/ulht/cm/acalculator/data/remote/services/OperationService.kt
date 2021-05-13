package ulht.cm.acalculator.data.remote.services

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ulht.cm.acalculator.data.remote.requests.Operation
import ulht.cm.acalculator.data.remote.responses.OperationResponse

interface OperationService {

    @POST("operations")
    suspend fun newOperation(@Header("Authorization") credentials: String ,@Body body: Operation): Response<OperationResponse>

    @GET("operations")
    suspend fun getOperation(@Header("Authorization") credentials: String): Response<ArrayList<OperationResponse>>

}