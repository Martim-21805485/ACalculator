package ulht.cm.acalculator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage private constructor(){

    private val storage = mutableListOf<Operation>()

    companion object{
        private var instance: ListStorage? = null

        fun getInstance(): ListStorage{

            synchronized(this){
                if(instance == null){
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    fun insert(operation: Operation){
        storage.add(operation)
    }

     fun remove(pos: Int){
         storage.removeAt(pos)
    }

    fun getAll(): List<Operation>{
        return  storage.toList()
    }
}