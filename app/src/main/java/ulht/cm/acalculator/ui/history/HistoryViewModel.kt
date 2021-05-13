package ulht.cm.acalculator.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.local.room.CalculatorDatabase
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.data.repositories.OperationRepository
import ulht.cm.acalculator.domain.history.HistoryLogic
import ulht.cm.acalculator.ui.calculator.ENDPOINT
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.listeners.OnListChanged

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val repository =  OperationRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val historicLogic = HistoryLogic(repository)



    fun onCreateList(callback: operations) {
        return  historicLogic.getAllOperations(callback)
    }

    fun onLongClick(pos: Int){
        /*
        historicLogic.removeFromList(pos)
        */

    }
}