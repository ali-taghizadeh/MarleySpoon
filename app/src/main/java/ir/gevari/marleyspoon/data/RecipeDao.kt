package ir.gevari.marleyspoon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.gevari.marleyspoon.data.db.entity.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateRecipeList(recipeList: List<Recipe>)

    @Query("select * from recipe_list")
    fun getRecipeList(): LiveData<List<Recipe>>

}