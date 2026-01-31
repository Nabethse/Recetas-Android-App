package com.myapplication.features.recipes.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class MealsResponseDto(
    @SerializedName("meals") val meals: List<MealDto>?
)