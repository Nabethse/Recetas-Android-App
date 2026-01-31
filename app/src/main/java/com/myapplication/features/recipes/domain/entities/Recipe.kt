package com.myapplication.features.recipes.domain.entities

data class Ingredient(val name: String, val measure: String)

data class Recipe(
    val id: String,
    val name: String,
    val imageUrl: String,
    val instructions: String? = null,
    val ingredients: List<Ingredient> = emptyList()
)