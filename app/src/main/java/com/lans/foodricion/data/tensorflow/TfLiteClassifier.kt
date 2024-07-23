package com.lans.foodricion.data.tensorflow

import android.content.Context
import android.graphics.Bitmap
import com.lans.foodricion.domain.model.Classification
import com.lans.foodricion.domain.tensorflow.FoodClassifier
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TfLiteClassifier(
    val context: Context
) : FoodClassifier {
    private val modelPath = "foodricionModel.tflite"
    private val labelPath = "labels.txt"

    private lateinit var interpreter: Interpreter
    private lateinit var labels: List<String>

    private fun setup() {
        val model = FileUtil.loadMappedFile(context, modelPath)
        interpreter = Interpreter(model)
        labels = FileUtil.loadLabels(context, labelPath)
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {
        setup()

        val inputShape = interpreter.getInputTensor(0).shape()
        val inputWidth = inputShape[1]
        val inputHeight = inputShape[2]
        val inputChannel = inputShape[3]
        val modelInputSize = 4 * inputWidth * inputHeight * inputChannel

        val resizedImage =
            Bitmap.createScaledBitmap(bitmap, inputWidth, inputHeight, true)
        val byteBuffer = ByteBuffer.allocateDirect(modelInputSize)
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(inputWidth * inputHeight)
        resizedImage.getPixels(
            pixels,
            0,
            resizedImage.width,
            0,
            0,
            resizedImage.width,
            resizedImage.height
        )
        var pixel = 0
        for (i in 0 until 200) {
            for (j in 0 until 200) {
                val value = pixels[pixel++]
                byteBuffer.putFloat((value shr 16 and 0xFF) / 127.5f)
                byteBuffer.putFloat((value shr 8 and 0xFF) / 127.5f)
                byteBuffer.putFloat((value and 0xFF) / 127.5f)
            }
        }

        val output = Array(1) { FloatArray(labels.size) }
        interpreter.run(byteBuffer, output)

        return output.flatMap {
            it.mapIndexed { index, fl ->
                Classification(
                    name = labels[index],
                    score = fl
                )
            }
        }.sortedByDescending { it.score }
    }
}