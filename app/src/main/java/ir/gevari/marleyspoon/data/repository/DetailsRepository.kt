package ir.gevari.marleyspoon.data.repository

import androidx.lifecycle.LiveData
import ir.gevari.marleyspoon.data.db.entity.Recipe

interface DetailsRepository {
    suspend fun getRecipeList(): LiveData<List<Recipe>>
}