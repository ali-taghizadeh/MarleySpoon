package ir.gevari.marleyspoon.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.gevari.marleyspoon.data.entity.Chef
import ir.gevari.marleyspoon.data.entity.Tags

class RecipeTypeConverters {

    @TypeConverter
    fun tagsToJson(value: List<Tags>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToTags(value: String): List<Tags>? {
        val objects = Gson().fromJson(value, Array<Tags>::class.java) as Array<Tags>
        return objects.toList()
    }

    @TypeConverter
    fun chefToJson(value: Chef?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToChef(value: String): Chef? {
        return Gson().fromJson(value, Chef::class.java) as Chef
    }

}