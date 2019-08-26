package ir.gevari.marleyspoon.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.gevari.marleyspoon.data.repository.DetailsRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val detailsRepository: DetailsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(detailsRepository) as T
    }
}