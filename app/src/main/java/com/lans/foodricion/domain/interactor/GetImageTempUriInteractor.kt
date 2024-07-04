package com.lans.foodricion.domain.interactor

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.lans.foodricion.domain.usecase.GetImageTempUriUseCase
import java.io.File
import javax.inject.Inject

class GetImageTempUriInteractor @Inject constructor(
    private val context: Context
) : GetImageTempUriUseCase {
    override fun invoke(authority: String): Uri {
        val directory = File(context.cacheDir, "images")
        directory.let { dir ->
            dir.mkdirs()
            val file = File.createTempFile(
                "image_" + System.currentTimeMillis().toString(),
                ".jpg",
                dir
            )

            return FileProvider.getUriForFile(
                context,
                authority,
                file
            )
        }
    }
}