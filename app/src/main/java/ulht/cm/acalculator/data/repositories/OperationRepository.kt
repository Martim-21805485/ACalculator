package ulht.cm.acalculator.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import ulht.cm.acalculator.data.local.room.dao.OperationDao
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.data.remote.services.OperationService
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.login.User
import java.util.*

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) {

    fun getOperations(callback: operations){
        val service = remote.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getOperation(User.getInstance().token)
            var listOperations = mutableListOf<Operation>()
            if (response.isSuccessful) {
                for (i in response.body()!!) {
                    listOperations.add(Operation(i.expression, i.result.toDouble()))
                }
            } else {
                listOperations = local.getAll() as MutableList<Operation>
            }
            callback.returnOperation(listOperations)
        }
    }

    fun addOperations(expression: String, result: Double ){
        saveOperationLocal(expression,result)
        saveOperationRemote(expression,result)
    }

    private fun getListOperations(): List<Operation> {
        return local.getAll()
    }

    private fun saveOperationLocal(expression: String, result: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            local.insert(Operation(expression, result))
        }
    }

    private fun saveOperationRemote(expression: String, result: Double) {
        val service = remote.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.newOperation(
                User.getInstance().token,
                ulht.cm.acalculator.data.remote.requests.Operation(
                    UUID.randomUUID().toString(),
                    expression,
                    result
                )
            )
        }
    }

    private fun getOperationsRemote() {
        val service = remote.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getOperation(User.getInstance().token)
            val listOperations = mutableListOf<Operation>()
            if (response.isSuccessful) {
                for (i in response.body()!!) {
                    listOperations.add(Operation(i.expression, i.result.toDouble()))
                }
            } else {
                println(response.errorBody()?.string())
            }
        }
    }
}