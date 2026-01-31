package com.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapplication.features.recipes.presentation.screens.RecipeDetailScreen
import com.myapplication.features.recipes.presentation.screens.RecipeSearchScreen

sealed class AppScreens(val route: String) {
    object RecipeSearchScreen : AppScreens("recipe_search")
    object RecipeDetailScreen : AppScreens("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_detail/$recipeId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.RecipeSearchScreen.route) {
        composable(AppScreens.RecipeSearchScreen.route) {
            RecipeSearchScreen(onRecipeClick = { recipeId ->
                navController.navigate(AppScreens.RecipeDetailScreen.createRoute(recipeId))
            })
        }
        composable(
            route = AppScreens.RecipeDetailScreen.route,
            arguments = listOf(navArgument("recipeId") { })
        ) {
            RecipeDetailScreen()
        }
    }
}