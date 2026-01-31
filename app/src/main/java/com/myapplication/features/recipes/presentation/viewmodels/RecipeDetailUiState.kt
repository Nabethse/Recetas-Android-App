package com.myapplication.features.recipes.presentation.viewmodels

import com.myapplication.features.recipes.domain.entities.Recipe

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val error: String? = null
)