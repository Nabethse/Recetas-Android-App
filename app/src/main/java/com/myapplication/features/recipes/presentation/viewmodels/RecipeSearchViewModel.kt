package com.myapplication.features.recipes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.features.recipes.domain.usecases.SearchRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeSearchViewModel(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeSearchUiState())
    val uiState: StateFlow<RecipeSearchUiState> = _uiState.asStateFlow()

    init {
        searchRecipes("a")
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            // Reset state for the new search
            _uiState.update { it.copy(isLoading = true, noResults = false, error = null) }
            try {
                val recipes = searchRecipesUseCase(query)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = recipes,
                        noResults = recipes.isEmpty()
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unexpected error occurred",
                        recipes = emptyList()
                    )
                }
            }
        }
    }
}