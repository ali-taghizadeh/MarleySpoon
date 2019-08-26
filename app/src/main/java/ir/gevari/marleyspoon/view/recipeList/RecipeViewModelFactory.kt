package ir.gevari.marleyspoon.view.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.gevari.marleyspoon.data.repository.RecipeListRepository

@Suppress("UNCHECKED_CAST")
class RecipeViewModelFactory(private val recipeListRepository: RecipeListRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeViewModel(recipeListRepository) as T
    }
}