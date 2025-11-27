package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Person
import Util.CensusAPIService
import Util.Util
import android.content.Context
import cr.ac.utn.census.R
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope

class PersonController(private val scope: LifecycleCoroutineScope) {
    private var dataManager: IDataManager = MemoryDataManager
    //private var context: Context

    /*constructor(context: Context) : this() {
        this.context=context
    }*/

    fun addPerson(person: Person){
        try {
            dataManager.add(person)
        }catch (e: Exception){
            //throw Exception(context
              //  .getString(R.string.ErrorMsgAdd))
            throw Exception(e.message)
        }
    }

    fun updatePerson(person: Person){
        try {
            dataManager.update(person)
        }catch (e: Exception){
            //throw Exception(context
               // .getString(R.string.ErrorMsgUpdate))
            throw Exception(e.message)
        }
    }

    fun getPeople(): List<Person>{
        var people = mutableListOf<Person>()
        scope.launch {
            try {
                val response = CensusAPIService.apiPeople.getAll()
                response.data.forEach { item ->
                    val person = Person()
                    person.ID= item.ID
                    person.Name= item.Name
                    person.FLastName= item.FLastName
                    person.SLastName= item.SLastName
                    person.Email= item.Email
                    person.Phone= item.Phone
                    person.Province= item.Province
                    person.State= item.State
                    person.Address= item.Address
                    people.add(person)
                    //person.Birthday= Util.parseStringToDateModern() item.Birthday
                    //person.Photo= item.Photo
                }
                // Update UI or log success
                Log.d("API_Call", "Success: ${response.data}")
            } catch (e: Exception) {
                // Handle error
                Log.e("API_Call", "Error fetching data: ${e.message}")
            }
        }
        return people
    }

    fun getById(id: String): Person?{
        try {
            return dataManager.getById(id)
        }catch (e: Exception){
            //throw Exception(context
              //  .getString(R.string.ErrorMsgGetById))
            throw Exception(e.message)
        }
    }

    fun getByFullName(fullname: String): Person?{
        try {
            return dataManager.
                getByFullName(fullname)
        }catch (e: Exception){
            //throw Exception(context
              //  .getString(R.string.ErrorMsgGetById))
            throw Exception(e.message)
        }
    }

    fun removePerson(id: String){
        try{
            val result = dataManager.getById(id)
            if (result == null){
                //throw Exception(context
                 //   .getString(R.string.MsgDataNoFound))
                throw Exception("Person no found")
            }
            dataManager.remove(id)
        }catch (e: Exception){
            //throw Exception(context
              //  .getString(R.string.ErrorMsgRemove))
            throw Exception(e.message)
        }
    }
}