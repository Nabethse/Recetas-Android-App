package com.myapplication.features.recipes.data.datasources.remote

import com.myapplication.features.recipes.data.datasources.remote.dto.MealsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun searchByName(@Query("s") name: String): MealsResponseDto

    @GET("lookup.php")
    suspend fun getRecipeDetails(@Query("i") id: String): MealsResponseDto
}