package ulht.cm.acalculator.ui.listeners

import ulht.cm.acalculator.data.local.room.entities.Operation

interface OnListChanged {
    fun onListChanged(value: List<Operation>)
}