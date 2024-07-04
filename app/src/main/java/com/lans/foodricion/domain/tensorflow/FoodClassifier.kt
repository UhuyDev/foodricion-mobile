package com.lans.foodricion.domain.tensorflow

import android.graphics.Bitmap
import com.lans.foodricion.domain.model.Classification

interface FoodClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}