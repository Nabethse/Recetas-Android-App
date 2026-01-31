package com.myapplication.features.recipes.data.repositories

import com.myapplication.features.recipes.data.datasources.remote.MealApi
import com.myapplication.features.recipes.data.mappers.toDomain
import com.myapplication.features.recipes.domain.entities.Recipe
import com.myapplication.features.recipes.domain.repositories.RecipeRepository

class RecipeRepositoryImpl(private val mealApi: MealApi) : RecipeRepository {
    override suspend fun searchRecipes(name: String): List<Recipe> {
        return mealApi.searchByName(name).meals?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun getRecipeDetails(id: String): Recipe? {
        return mealApi.getRecipeDetails(id).meals?.firstOrNull()?.toDomain()
    }
}