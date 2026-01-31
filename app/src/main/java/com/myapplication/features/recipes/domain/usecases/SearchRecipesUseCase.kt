package com.myapplication.features.recipes.domain.usecases

import com.myapplication.features.recipes.domain.entities.Recipe
import com.myapplication.features.recipes.domain.repositories.RecipeRepository

class SearchRecipesUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(name: String): List<Recipe> {
        return recipeRepository.searchRecipes(name)
    }
}