package util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class util {
    companion object{
        fun OpenActivity(context: Context, objClassActivity: Class<*>){
            val objIntent = Intent(context
                , objClassActivity)
            context.startActivity(objIntent)
        }
    }
}