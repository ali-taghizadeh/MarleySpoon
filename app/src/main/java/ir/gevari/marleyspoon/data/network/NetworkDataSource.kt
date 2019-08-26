package ir.gevari.marleyspoon.data.network

import androidx.lifecycle.LiveData
import ir.gevari.marleyspoon.data.db.entity.Recipe

interface NetworkDataSource {
    val downloadedRecipeList: LiveData<List<Recipe>>
    suspend fun fetchRecipeList()
}