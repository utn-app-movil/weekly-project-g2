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
import java.time.LocalDate

class PersonController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context=context
    }

    fun addPerson(person: Person){
        try {
            dataManager.add(person)
        }catch (e: Exception){
            throw Exception(context
                .getString(R.string.ErrorMsgAdd))

        }
    }

    fun updatePerson(person: Person){
        try {
            dataManager.update(person)
        }catch (e: Exception){
            throw Exception(context
                .getString(R.string.ErrorMsgUpdate))

        }
    }

    suspend fun getPeople(): List<Person>{
        var people = mutableListOf<Person>()
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
                val bDateParse = Util.parseStringToDateModern(item.Birthday,
                    "dd/MM/yyyy")
                person.Birthday = LocalDate.of(bDateParse?.year!!, bDateParse.month.value
                    , bDateParse?.dayOfMonth!!)
                people.add(person)
                //person.Photo= item.Photo
            }
            Log.d("API_Call", "Success: ${response.data}")
        } catch (e: Exception) {
            // Handle error
            Log.e("API_Call", "Error fetching data: ${e.message}")
            throw Exception(e.message)
        }
        return people
    }

    suspend fun getById(id: String): Person?{
        var person: Person?
        try {
            person = null
            val response = CensusAPIService.apiPeople.getbyId(id)
            Log.d("API_Call", "Success: ${response.data}")
            if (response.data.any()){
                val item = response.data[0]
                person = Person()
                person.ID= item.ID
                person.Name= item.Name
                person.FLastName= item.FLastName
                person.SLastName= item.SLastName
                person.Email= item.Email
                person.Phone= item.Phone
                person.Province= item.Province
                person.State= item.State
                person.Address= item.Address
                val bDateParse = Util.parseStringToDateModern(item.Birthday,
                    "dd/MM/yyyy")
                person.Birthday = LocalDate.of(bDateParse?.year!!, bDateParse.month.value
                    , bDateParse?.dayOfMonth!!)
                //person.Photo= item.Photo
            }
        }catch (e: Exception){
            throw Exception(context
                .getString(R.string.ErrorMsgGetById))
        }
        return person
    }

    fun removePerson(id: String){
        try{
            val result = dataManager.getById(id)
            if (result == null){
                throw Exception(context
                    .getString(R.string.MsgDataNoFound))
            }
            dataManager.remove(id)
        }catch (e: Exception){
            throw Exception(context
                .getString(R.string.ErrorMsgRemove))
        }
    }
}