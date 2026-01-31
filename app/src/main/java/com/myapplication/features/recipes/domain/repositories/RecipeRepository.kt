package com.myapplication.features.recipes.domain.repositories

import com.myapplication.features.recipes.domain.entities.Recipe

interface RecipeRepository {
    suspend fun searchRecipes(name: String): List<Recipe>
    suspend fun getRecipeDetails(id: String): Recipe?
}