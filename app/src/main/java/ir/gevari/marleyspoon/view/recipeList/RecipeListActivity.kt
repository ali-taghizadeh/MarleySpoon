package ir.gevari.marleyspoon.view.recipeList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.gevari.marleyspoon.R
import ir.gevari.marleyspoon.view.base.ScopedActivity
import kotlinx.android.synthetic.main.activity_recipe_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class RecipeListActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: RecipeViewModelFactory by instance()
    private lateinit var viewModel: RecipeViewModel

    private val adapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RecipeViewModel::class.java)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        bindUI()
    }

    private fun bindUI() = launch {
        val recipeList = viewModel.recipeList.await()
        recipeList.observe(this@RecipeListActivity, Observer {
            if (it == null || it.isEmpty()) return@Observer
            progressBar.visibility = View.GONE
            adapter.submitList(it)
        })
    }
}
