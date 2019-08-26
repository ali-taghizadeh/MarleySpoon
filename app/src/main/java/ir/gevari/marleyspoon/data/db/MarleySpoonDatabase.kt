package ir.gevari.marleyspoon.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.gevari.marleyspoon.data.RecipeDao
import ir.gevari.marleyspoon.data.RecipeTypeConverters
import ir.gevari.marleyspoon.data.db.entity.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(RecipeTypeConverters::class)

abstract class MarleySpoonDatabase : RoomDatabase() {
    abstract fun recipeListDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: MarleySpoonDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(
            LOCK
        ) {
            instance
                ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MarleySpoonDatabase::class.java, "MarleySpoon.db"
            )
                .build()
    }
}