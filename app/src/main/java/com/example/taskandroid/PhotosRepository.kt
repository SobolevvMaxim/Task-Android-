package com.example.taskandroid

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotosRepository(
    private val dispatcher: CoroutineDispatcher
) {
    private var addedPhotos: List<ImageItem>? = null

    suspend fun writeImageToBase(image: ImageItem): List<ImageItem> {
        withContext(dispatcher) {
            // TODO: dao insert city in base
            insertInMemory(image)
        }

        return addedPhotos ?: emptyList()
    }

    private fun insertInMemory(image: ImageItem) {
        addedPhotos = addedPhotos?.toMutableList()?.let { photos ->
            photos.add(image)
            photos
        } ?: listOf(image)
    }
}