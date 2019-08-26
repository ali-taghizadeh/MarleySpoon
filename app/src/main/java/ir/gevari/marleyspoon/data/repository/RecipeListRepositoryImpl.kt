package ir.gevari.marleyspoon.data.repository

import androidx.lifecycle.LiveData
import ir.gevari.marleyspoon.data.RecipeDao
import ir.gevari.marleyspoon.data.db.entity.Recipe
import ir.gevari.marleyspoon.data.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListRepositoryImpl(
    private val recipeListDao: RecipeDao,
    private val networkDataSource: NetworkDataSource
) : RecipeListRepository {

    /**
     * So it always fetches data from network first and then it persists the
     * fetched data into roomDb
     */

    init {
        networkDataSource.apply {
            downloadedRecipeList.observeForever { NewRecipeList ->
                persistFetchedRecipeList(NewRecipeList)
            }
        }
    }

    private fun persistFetchedRecipeList(NewRecipeList: List<Recipe>) {
        GlobalScope.launch(Dispatchers.IO) {
            recipeListDao.updateRecipeList(NewRecipeList)
        }
    }

    override suspend fun getRecipeList(): LiveData<List<Recipe>> {
        return withContext(Dispatchers.IO) {
            networkDataSource.fetchRecipeList()
            return@withContext recipeListDao.getRecipeList()
        }
    }
}