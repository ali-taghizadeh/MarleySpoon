package ir.gevari.marleyspoon.data.network

import com.contentful.java.cda.CDAAsset
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.image.ImageOption
import ir.gevari.marleyspoon.data.db.entity.Chef
import ir.gevari.marleyspoon.data.db.entity.Recipe
import ir.gevari.marleyspoon.data.db.entity.Tags

const val TITLE = "title"
const val DESCRIPTION = "description"
const val CHEF = "chef"
const val TAGS = "tags"
const val PHOTO = "photo"
const val UNKNOWN = "Unknown"
const val NAME = "name"

fun Recipe.Companion.mapToRecipe(entry: CDAEntry): Recipe = Recipe(
    entry.getField<String?>(TITLE).orEmpty(),
    entry.getField<String?>(DESCRIPTION).orEmpty(),
    if (entry.getField<CDAEntry?>(CHEF) == null) Chef(UNKNOWN)
    else Chef.mapToChef(entry.getField(CHEF)),
    entry.getField<List<CDAEntry>?>(TAGS).orEmpty().map { Tags.mapToTags(it) },
    try {
        entry.getField<CDAAsset?>(PHOTO)
            ?.urlForImageWith(ImageOption.https(), ImageOption.formatOf(ImageOption.Format.webp))
            .orEmpty()
    } catch (t: Throwable) {
        ""
    }
)

fun Tags.Companion.mapToTags(entry: CDAEntry) = Tags(
    entry.getField<String?>(NAME).orEmpty()
)

fun Chef.Companion.mapToChef(entry: CDAEntry) = Chef(
    entry.getField<String?>(NAME).orEmpty()
)