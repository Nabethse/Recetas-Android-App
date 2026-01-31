package com.myapplication.features.recipes.presentation.viewmodels

import com.myapplication.features.recipes.domain.entities.Recipe

data class RecipeSearchUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String? = null,
    val noResults: Boolean = false
)