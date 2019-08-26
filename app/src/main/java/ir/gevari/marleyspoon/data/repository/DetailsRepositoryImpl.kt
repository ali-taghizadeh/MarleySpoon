package ir.gevari.marleyspoon.data.repository

import androidx.lifecycle.LiveData
import ir.gevari.marleyspoon.data.RecipeDao
import ir.gevari.marleyspoon.data.db.entity.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepositoryImpl(
    private val recipeListDao: RecipeDao
) : DetailsRepository {

    /**
     * This time it just fetches data from room because it's
     * persisted before
     */

    override suspend fun getRecipeList(): LiveData<List<Recipe>> {
        return withContext(Dispatchers.IO) {
            return@withContext recipeListDao.getRecipeList()
        }
    }

}