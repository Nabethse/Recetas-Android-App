package com.myapplication.features.recipes.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.myapplication.features.recipes.data.datasources.remote.MealApi
import com.myapplication.features.recipes.data.repositories.RecipeRepositoryImpl
import com.myapplication.features.recipes.domain.usecases.GetRecipeDetailsUseCase
import com.myapplication.features.recipes.presentation.viewmodels.RecipeDetailViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun RecipeDetailScreen() {
    val viewModel: RecipeDetailViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val savedStateHandle = extras.createSavedStateHandle()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val mealApi = retrofit.create(MealApi::class.java)
                val repository = RecipeRepositoryImpl(mealApi)
                val useCase = GetRecipeDetailsUseCase(repository)
                @Suppress("UNCHECKED_CAST")
                return RecipeDetailViewModel(useCase, savedStateHandle) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        return
    }

    uiState.error?.let {
        Text(text = it, color = MaterialTheme.colorScheme.error)
        return
    }

    uiState.recipe?.let { recipe ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = recipe.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ingredients", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            recipe.ingredients.forEach { ingredient ->
                Text(text = "• ${ingredient.name} - ${ingredient.measure}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Instructions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recipe.instructions ?: "No instructions available.")
        }
    }
}