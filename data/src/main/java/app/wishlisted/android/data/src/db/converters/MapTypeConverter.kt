package app.wishlisted.android.data.src.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapTypeConverter {

    val bundleParameterizedType = object : TypeToken<Map<String, String>>() {}

    @TypeConverter
    fun fromMap(bundle: Map<String, String>?): String? {
        if (bundle == null || bundle.isEmpty()) {
            return null
        }

        return Gson().toJson(bundle)
    }

    @TypeConverter
    fun toMap(jsonString: String?): Map<String, String>? {
        if (jsonString == null || jsonString.isEmpty()) {
            return null
        }

        return Gson().fromJson(jsonString, bundleParameterizedType.type)
    }
}
