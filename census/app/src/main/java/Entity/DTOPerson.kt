package Entity

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class DTOPerson(@SerializedName("personId") val ID: String,
                     @SerializedName("name") val Name: String,
            @SerializedName("fLastName") val FLastName: String,
            @SerializedName("sLastName") val SLastName: String,
            @SerializedName("phone") val Phone: Int,
            @SerializedName("email") val Email: String,
            @SerializedName("birthdate") val Birthday: String,
             @SerializedName("province") val Province: DTOProvince,
             @SerializedName("state") val State: String="",
             @SerializedName("district") val District: String="",
             @SerializedName("address") val Address: String,
             @SerializedName("lat") val Latitude: Int,
             @SerializedName("long") val Longitude: Int,
             @SerializedName("photo_url") val Photo: String)
