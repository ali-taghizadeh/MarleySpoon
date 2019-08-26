package ir.gevari.marleyspoon.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.contentful.java.cda.CDAEntry
import ir.gevari.marleyspoon.BuildConfig
import ir.gevari.marleyspoon.data.db.entity.Recipe
import java.io.IOException


class NetworkDataSourceImpl : NetworkDataSource {

    private val _downloadedMasterList = MutableLiveData<List<Recipe>>()
    override val downloadedRecipeList: LiveData<List<Recipe>>
        get() = _downloadedMasterList

    override suspend fun fetchRecipeList() {
        if (isOnline()) {
            try {
                val fetchedMasterList = ApiClient.invoke().fetch(CDAEntry::class.java)
                    .withContentType(BuildConfig.CONTENT_TYPE)
                    .include(10)
                    .all()
                    .items()
                    .map {
                        Recipe.mapToRecipe(
                            it as CDAEntry
                        )
                    }
                _downloadedMasterList.postValue(fetchedMasterList)
            } catch (ex: IOException) {
                Log.e("exception", ex.message)
            }
        }
    }
}