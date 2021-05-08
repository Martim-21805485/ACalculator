package ulht.cm.acalculator.domain.history

import ulht.cm.acalculator.data.local.list.ListStorage
import ulht.cm.acalculator.data.local.room.entities.Operation

class HistoryLogic {

    private val storage = ListStorage.getInstance()

    fun getAllOperations(): List<Operation>{
        return storage.getAll()
    }

    fun removeFromList(pos: Int){
        storage.remove(pos)
    }
}