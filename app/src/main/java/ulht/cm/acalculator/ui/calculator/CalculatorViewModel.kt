package ulht.cm.acalculator.ui.calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ulht.cm.acalculator.data.local.room.CalculatorDatabase
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.data.repositories.OperationRepository
import ulht.cm.acalculator.domain.calculator.CalculatorLogic
import ulht.cm.acalculator.ui.callback.login
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.listeners.OnDisplayChanged

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val repository =  OperationRepository(storage, RetrofitBuilder.getInstance(ulht.cm.acalculator.ui.calculator.ENDPOINT))
    private val calculatorLogic = CalculatorLogic(repository)

    private var listener: OnDisplayChanged? = null
    var display: String = ""

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener?.onDisplayChanged(display)
    }

    fun unregisterListener(){
        listener = null
    }

    fun onClickSymbol(symbol: String): String{
        display = calculatorLogic.insertSymbol(display,symbol)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickEquals(): String{
        val result = calculatorLogic.perfomOperation(display)
        display = result.toString()
        notifyOnDisplayChanged()
        return display
    }

    fun onClickDelete(): String{
        val result = calculatorLogic.clearAll()
        display = result
        notifyOnDisplayChanged()
        return display
    }

    fun onClickDeleteLastChar(): String{
        val result = calculatorLogic.deleteLastChar(display)
        display = result
        notifyOnDisplayChanged()
        return display
    }

    fun onShowList(callback: operations){
        calculatorLogic.getListOperations(callback)
    }


}