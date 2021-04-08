package app.wishlisted.android.data.src.db

import androidx.room.TypeConverter
import app.wishlisted.android.data.src.model.GameDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import java.io.IOException

object StringConverters {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value == null) return emptyList()
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<String>>(
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ).fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        if (list == null) return ""
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<String>>(
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ).toJson(list)
    }
}

object GameImageConverters {

    @TypeConverter
    fun fromString(value: String?): List<GameDTO.Image> {
        if (value == null) return emptyList()
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Image>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Image::class.java
            )
        ).fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<GameDTO.Image>?): String {
        if (list == null) return ""
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Image>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Image::class.java
            )
        ).toJson(list)
    }
}

object GameVideoConverters {

    @TypeConverter
    fun fromString(value: String?): List<GameDTO.Video> {
        if (value == null) return emptyList()
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Video>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Video::class.java
            )
        ).fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<GameDTO.Video>?): String {
        if (list == null) return ""
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Video>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Video::class.java
            )
        ).toJson(list)
    }
}

object AttributeConverters {

    @TypeConverter
    fun fromString(value: String?): List<GameDTO.Attribute> {
        if (value == null) return emptyList()
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Attribute>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Attribute::class.java
            )
        ).fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<GameDTO.Attribute>?): String {
        if (list == null) return ""
        val moshi = Moshi.Builder().build()

        return moshi.adapter<List<GameDTO.Attribute>>(
            Types.newParameterizedType(
                List::class.java,
                GameDTO.Attribute::class.java
            )
        ).toJson(list)
    }
}