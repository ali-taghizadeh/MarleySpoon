package ir.gevari.marleyspoon.view.recipeList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.gevari.marleyspoon.R
import ir.gevari.marleyspoon.data.db.entity.Recipe
import ir.gevari.marleyspoon.view.details.DetailsActivity
import kotlinx.android.synthetic.main.item_details.view.*


class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DetailsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_details, parent, false)
        ) {
        fun bind(detail: Recipe) {
            Glide
                .with(itemView.context)
                .load(detail.photo)
                .apply(RequestOptions.fitCenterTransform())
                .into(itemView.imageViewPhoto)

            itemView.textViewTitle.text = detail.title
            /**
             * By clicking on a card, the position of the tapped item
             * will be sent to the DetailsActivity
             */
            itemView.cardViewItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt(itemView.context.getString(R.string.item_position), adapterPosition)
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (itemView.context) as Activity,
                        itemView.imageViewPhoto,
                        itemView.context.getString(R.string.shared_element)
                    )
                intent.putExtras(bundle)
                itemView.context.startActivity(intent, options.toBundle())
            }
        }
    }
}

private class DetailsDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem
}
