package com.example.onboarding_domain.use_case

import com.example.core.R
import com.example.core.util.UiText

class ValidateNutrients {

    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String,
    ): Result {
        var carbsRatio = carbsRatioText.toIntOrNull()
        var proteinRatio = proteinRatioText.toIntOrNull()
        var fatRatio = fatRatioText.toIntOrNull()

        if (carbsRatio == null || proteinRatio == null || fatRatio == null){
            return Result.Error (
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }
        if (carbsRatio + proteinRatio + fatRatio != 100){
            return Result.Error (
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbsRatio / 100f,
            proteinRatio / 100f,
            fatRatio / 100f
        )
    }

    sealed class Result {

        data class Success(
            val cabsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float,
        ): Result()

        data class Error(val message: UiText): Result()
    }

}