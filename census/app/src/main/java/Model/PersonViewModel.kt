package Model

import Entity.Person
import Util.CensusAPIService
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    fun getPeople() {
        var people = mutableListOf<Person>()
        viewModelScope.launch {
            try {
                val response = CensusAPIService.apiPeople.getAll()
                response.data.forEach { item ->
                    val person = Person()
                    person.ID = item.ID
                    person.Name = item.Name
                    person.FLastName = item.FLastName
                    person.SLastName = item.SLastName
                    person.Email = item.Email
                    person.Phone = item.Phone
                    person.Province = item.Province
                    person.State = item.State
                    person.Address = item.Address
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

    }
}