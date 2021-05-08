package ulht.cm.acalculator.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ulht.cm.acalculator.data.local.room.entities.Operation


@Dao
interface OperationDao {

    @Insert
     fun insert(operation: Operation)

    @Query("SELECT * FROM operation")
     fun getAll(): List<Operation>

    @Query("SELECT * FROM operation WHERE uuid= :uuid")
     fun  getById(uuid: String): Operation
}