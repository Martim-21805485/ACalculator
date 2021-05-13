package ulht.cm.acalculator.domain.calculator

import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import org.json.JSONObject
import retrofit2.Retrofit
import ulht.cm.acalculator.data.local.room.dao.OperationDao
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.data.remote.services.OperationService
import ulht.cm.acalculator.data.repositories.OperationRepository
import ulht.cm.acalculator.ui.callback.login
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.login.User
import java.util.*


class CalculatorLogic(private val repository: OperationRepository) {


    fun insertSymbol(display: String, symbol: String): String {
        var displayF = display
        if (display == "0") {
            displayF = symbol
        } else {
            displayF += (symbol)
        }
        return displayF
    }

    fun deleteLastChar(display: String): String {
        var displayF = ""
        if (display.isNotEmpty()) {
            displayF = display.substring(0, display.length - 1);
        }
        return displayF
    }

    fun clearAll(): String {
        return ""
    }


    fun perfomOperation(expression: String): Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        repository.addOperations(expression,result)
        return result
    }



    fun getListOperations(callback: operations){
        repository.getOperations(callback)
    }


}
