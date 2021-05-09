package ulht.cm.acalculator.domain.calculator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import ulht.cm.acalculator.data.local.room.dao.OperationDao
import ulht.cm.acalculator.data.local.room.entities.Operation

class CalculatorLogic(private val storage: OperationDao) {


    fun insertSymbol( display: String, symbol: String): String{
        var displayF = display
        if(display == "0"){
            displayF = symbol
        }else{
            displayF +=(symbol)
        }
        return displayF
    }

    fun deleteLastChar( display: String): String{
        var displayF = ""
        if (display.isNotEmpty()) {
            displayF = display.substring(0, display.length - 1);
        }
        return displayF
    }

    fun clearAll(): String{
        return  ""
    }

     fun getListOperations(): List<Operation>{
        return storage.getAll()
    }

     fun perfomOperation(expression: String): Double{
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(Operation(expression,result))
        }
        return result
    }
}
