package com.myapplication.features.recipes.domain.usecases

import com.myapplication.features.recipes.domain.entities.Recipe
import com.myapplication.features.recipes.domain.repositories.RecipeRepository

class GetRecipeDetailsUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: String): Recipe? {
        return repository.getRecipeDetails(id)
    }
}