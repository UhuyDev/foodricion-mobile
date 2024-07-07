package com.lans.foodricion.data.tensorflow

import android.content.Context
import android.graphics.Bitmap
import android.view.Surface
import com.lans.foodricion.domain.model.Classification
import com.lans.foodricion.domain.tensorflow.FoodClassifier
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class TfLiteClassifier(
    val context: Context
) : FoodClassifier {
    private var imageClassifier: ImageClassifier? = null
    private val threshold: Float = 0.5f
    private val numThreads: Int = 2
    private val maxResults: Int = 3

    private fun setupClassifier() {
        val baseOptions = BaseOptions.builder()
            .setNumThreads(numThreads)
            .build()

        val optionsBuilder = ImageClassifier
            .ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)
            .setBaseOptions(baseOptions)

        val modelName = "foodModel.tflite"

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelName,
                optionsBuilder.build()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {
        if (imageClassifier == null) {
            setupClassifier()
        }

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(0f, 1f))
            .build()

        val processedTensorImage = imageProcessor.process(tensorImage)
        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOrientationFromRotation(0))
            .build()

        val results = imageClassifier?.classify(processedTensorImage, imageProcessingOptions)
        return results?.flatMap { classifications ->
            classifications.categories.map { category ->
                Classification(
                    name = category.displayName,
                    score = category.score
                )
            }
        }?.distinctBy { it.name } ?: emptyList()
    }

    private fun getOrientationFromRotation(rotation: Int): ImageProcessingOptions.Orientation {
        return when (rotation) {
            Surface.ROTATION_270 ->
                ImageProcessingOptions.Orientation.BOTTOM_RIGHT

            Surface.ROTATION_180 ->
                ImageProcessingOptions.Orientation.RIGHT_BOTTOM

            Surface.ROTATION_90 ->
                ImageProcessingOptions.Orientation.TOP_LEFT

            else ->
                ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }
}