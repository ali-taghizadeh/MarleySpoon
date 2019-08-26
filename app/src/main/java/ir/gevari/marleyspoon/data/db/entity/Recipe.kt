package ir.gevari.marleyspoon.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_list")
data class Recipe(
    @PrimaryKey
    val title: String,
    val description: String,
    val chef: Chef,
    val tags: List<Tags>,
    val photo: String
) {
    companion object
}

data class Tags(val name: String) {
    companion object
}

data class Chef(val name: String) {
    companion object
}