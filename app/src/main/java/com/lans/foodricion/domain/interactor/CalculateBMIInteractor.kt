package com.lans.foodricion.domain.interactor

import com.lans.foodricion.domain.model.BMIResult
import com.lans.foodricion.domain.usecase.CalculateBMIUseCase
import kotlin.math.pow

class CalculateBMIInteractor : CalculateBMIUseCase {
    override suspend fun invoke(height: Double, weight: Double): BMIResult {
        val heightInMeter = height / 100
        val bmiScore = if (heightInMeter > 0) weight / heightInMeter.pow(2) else 0.0
        val bmiCategory = when {
            bmiScore < 18.5 -> "Underweight"
            bmiScore in 18.5..24.9 -> "Healthy Weight"
            bmiScore in 25.0..29.9 -> "Pre-Obesity"
            bmiScore in 30.0..34.9 -> "Obesity Class I"
            bmiScore in 35.0..39.9 -> "Obesity Class II"
            bmiScore >= 40.0 -> "Obesity Class III"
            else -> "Category"
        }
        val bmiMessage = when (bmiCategory) {
            "Underweight" -> "Your BMI of less than 18.5 indicates that you are underweight, so you may need to put on some weight. You are recommended to ask your doctor or a dietitian for advice."
            "Healthy Weight" -> "Congratulations! Your BMI indicates that you are at a healthy weight for your height. By maintaining a healthy weight, you lower your risk of developing serious health problems."
            "Pre-Obesity" -> "Your BMI of 25-29.9 indicates that you are slightly overweight. You may be advised to lose some weight for health reasons. You are recommended to talk to your doctor or a dietitian for advice."
            "Obesity Class I" -> "Your BMI of 30-34.9 indicates that you are heavily overweight. Your health may be at risk if you do not lose weight. You are recommended to talk to your doctor or a dietitian for advice."
            "Obesity Class II" -> "Your BMI of 35-39.9 indicates that you are heavily overweight. Your health may be at risk if you do not lose weight. You are recommended to talk to your doctor or a dietitian for advice."
            "Obesity Class III" -> "Your BMI of 40 or higher indicates that you are heavily overweight. Your health may be at risk if you do not lose weight. You are recommended to talk to your doctor or a dietitian for advice."
            else -> ""
        }

        return BMIResult(
            score = "%.2f".format(bmiScore),
            category = bmiCategory,
            message = bmiMessage
        )
    }
}