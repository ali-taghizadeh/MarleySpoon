package ir.gevari.marleyspoon.view.recipeList

import androidx.lifecycle.ViewModel
import ir.gevari.marleyspoon.data.repository.RecipeListRepository
import ir.gevari.marleyspoon.utils.lazyDeferred

class RecipeViewModel(private val recipeListRepository: RecipeListRepository) : ViewModel() {
    val recipeList by lazyDeferred {
        recipeListRepository.getRecipeList()
    }
}
