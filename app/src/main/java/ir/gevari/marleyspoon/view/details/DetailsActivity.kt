package ir.gevari.marleyspoon.view.details

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.backgroundColor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.gevari.marleyspoon.R
import ir.gevari.marleyspoon.view.base.ScopedActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_recipe_list.collapsing
import kotlinx.android.synthetic.main.activity_recipe_list.imageViewPhoto
import kotlinx.android.synthetic.main.activity_recipe_list.toolbar
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
            val position: Int = intent?.extras?.get(getString(R.string.item_position)) as Int
            Glide
                .with(this@DetailsActivity)
                .load(it[position].photo)
                .apply(RequestOptions.fitCenterTransform())
                .into(imageViewPhoto)
            collapsing.title = it[position].title
            textViewChef.text = it[position].chef.name
            textViewDescription.text = it[position].description
            val tags = SpannableStringBuilder()
            for (tag in it[position].tags)
                tags.backgroundColor(ContextCompat.getColor(this@DetailsActivity, R.color.gray)) {
                    append(" " + tag.name + " ")
                }
                    .append(" ")
            textViewTags.text = tags
        })
    }
}
