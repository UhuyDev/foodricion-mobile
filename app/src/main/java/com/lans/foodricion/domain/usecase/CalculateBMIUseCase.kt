package com.lans.foodricion.domain.usecase

import com.lans.foodricion.domain.model.BMIResult

interface CalculateBMIUseCase {
    suspend fun invoke(height: Double, weight: Double): BMIResult
}