package ulht.cm.acalculator.domain.history

import ulht.cm.acalculator.data.local.list.ListStorage
import ulht.cm.acalculator.data.local.room.CalculatorDatabase
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.data.remote.RetrofitBuilder
import ulht.cm.acalculator.data.repositories.OperationRepository
import ulht.cm.acalculator.ui.callback.operations

class HistoryLogic(private val repository: OperationRepository) {




    fun getAllOperations(callback: operations){
         repository.getOperations(callback)
    }


}