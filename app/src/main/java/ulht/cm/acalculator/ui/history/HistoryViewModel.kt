package ulht.cm.acalculator.ui.history

import androidx.lifecycle.ViewModel
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.domain.history.HistoryLogic
import ulht.cm.acalculator.ui.listeners.OnListChanged

class HistoryViewModel: ViewModel() {
    private val historicLogic = HistoryLogic()
    private var listener: OnListChanged? = null

    private fun notifyOnListChanged(){
        listener?.onListChanged(historicLogic.getAllOperations())
    }

    fun registerListener(listener: OnListChanged){
        this.listener = listener
        listener?.onListChanged(historicLogic.getAllOperations())
    }

    fun unregisterListener(){
        listener = null
    }

    fun onCreateList(): List<Operation>{
        return  historicLogic.getAllOperations()
    }

    fun onLongClick(pos: Int){
        historicLogic.removeFromList(pos)
        notifyOnListChanged()
    }
}