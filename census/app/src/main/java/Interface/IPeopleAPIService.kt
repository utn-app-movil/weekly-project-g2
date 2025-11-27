package Interface

import Entity.DTOPerson
import Entity.PersonGetResponse
import retrofit2.http.*
import retrofit2.Call

interface IPeopleAPIService {
    @GET("/people")
    suspend fun getAll(): PersonGetResponse

    @GET("/people/{id")
    suspend fun getbyId(@Path("id") personId: String): PersonGetResponse

    @Headers("Content-Type: application/json")
    @POST("/people")
    suspend fun postPerson (@Body person: DTOPerson): PersonGetResponse
}