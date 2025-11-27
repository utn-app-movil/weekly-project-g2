package Interface

import Entity.PersonGetResponse
import retrofit2.http.*
import retrofit2.Call

interface IPeopleAPIService {
    @GET("/people")
    suspend fun getAll(): PersonGetResponse
}