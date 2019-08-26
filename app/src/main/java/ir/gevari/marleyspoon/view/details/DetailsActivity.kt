package ir.gevari.marleyspoon.view.details

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.gevari.marleyspoon.R
import ir.gevari.marleyspoon.view.base.ScopedActivity
import kotlinx.android.synthetic.main.activity_recipe_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class DetailsActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: DetailsViewModelFactory by instance()
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val details = viewModel.details.await()
        details.observe(this@DetailsActivity, Observer {
            if (it == null || it.isEmpty()) return@Observer
        })
    }
}
