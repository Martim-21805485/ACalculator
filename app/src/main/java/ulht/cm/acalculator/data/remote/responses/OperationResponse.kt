package ulht.cm.acalculator.data.remote.responses

import com.google.gson.annotations.SerializedName

class OperationResponse {

    @SerializedName("uuid")
    var uuid: String = ""

    @SerializedName("expression")
    var expression: String = ""

    @SerializedName("result")
    var result: String = ""

}