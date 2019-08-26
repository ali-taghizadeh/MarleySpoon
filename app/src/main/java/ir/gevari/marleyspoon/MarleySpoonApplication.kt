package ir.gevari.marleyspoon

import android.app.Application
import ir.gevari.marleyspoon.data.db.MarleySpoonDatabase
import ir.gevari.marleyspoon.data.network.NetworkDataSource
import ir.gevari.marleyspoon.data.network.NetworkDataSourceImpl
import ir.gevari.marleyspoon.data.repository.RecipeListRepository
import ir.gevari.marleyspoon.data.repository.RecipeListRepositoryImpl
import ir.gevari.marleyspoon.view.recipeList.RecipeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class MarleySpoonApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MarleySpoonApplication))
        bind() from singleton { MarleySpoonDatabase(instance()) }
        bind() from singleton { instance<MarleySpoonDatabase>().recipeListDao() }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl() }
        bind<RecipeListRepository>() with singleton { RecipeListRepositoryImpl(instance(), instance()) }
        bind() from provider { RecipeViewModelFactory(instance()) }
    }
}