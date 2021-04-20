package ulht.cm.acalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    private val calculatorLogic = CalculatorLogic()
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

    fun onShowList(): List<Operation>{
        return calculatorLogic.getListOperations()
    }


}