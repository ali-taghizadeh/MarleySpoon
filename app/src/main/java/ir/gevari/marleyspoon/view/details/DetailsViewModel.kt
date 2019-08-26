package ir.gevari.marleyspoon.view.details

import androidx.lifecycle.ViewModel
import ir.gevari.marleyspoon.data.repository.DetailsRepository
import ir.gevari.marleyspoon.utils.lazyDeferred

class DetailsViewModel(private val detailsRepository: DetailsRepository) : ViewModel() {
    val details by lazyDeferred {
        detailsRepository.getRecipeList()
    }
}
