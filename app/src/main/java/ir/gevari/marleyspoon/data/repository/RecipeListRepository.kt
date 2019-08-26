package ir.gevari.marleyspoon.data.repository

import androidx.lifecycle.LiveData
import ir.gevari.marleyspoon.data.db.entity.Recipe

interface RecipeListRepository {
    suspend fun getRecipeList(): LiveData<List<Recipe>>
}