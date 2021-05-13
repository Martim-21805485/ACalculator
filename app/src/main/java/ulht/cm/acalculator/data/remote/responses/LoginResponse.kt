package ulht.cm.acalculator.data.remote.responses

import com.google.gson.annotations.SerializedName

class LoginResponse() {

    @SerializedName("email")
    var email: String = ""
    @SerializedName("token")
    var token: String = ""
}