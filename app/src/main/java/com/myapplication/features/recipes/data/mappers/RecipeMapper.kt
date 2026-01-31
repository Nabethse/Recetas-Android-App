package com.myapplication.features.recipes.data.mappers

import com.myapplication.features.recipes.data.datasources.remote.dto.MealDto
import com.myapplication.features.recipes.domain.entities.Ingredient
import com.myapplication.features.recipes.domain.entities.Recipe

fun MealDto.toDomain(): Recipe {
    val ingredients = mutableListOf<Ingredient>()
    val ingredientList = listOf(
        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
        ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, ingredient16, ingredient17, ingredient18, ingredient19, ingredient20
    )
    val measureList = listOf(
        measure1, measure2, measure3, measure4, measure5, measure6, measure7, measure8, measure9, measure10,
        measure11, measure12, measure13, measure14, measure15, measure16, measure17, measure18, measure19, measure20
    )

    for (i in ingredientList.indices) {
        if (!ingredientList[i].isNullOrBlank()) {
            ingredients.add(Ingredient(ingredientList[i]!!, measureList[i] ?: ""))
        }
    }

    return Recipe(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        instructions = this.instructions,
        ingredients = ingredients
    )
}