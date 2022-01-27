package com.example.taskandroid

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.*

class ImagesRepository(
    private val imagesDao: ImagesDao,
    private val dispatcher: CoroutineDispatcher
) {
    private var addedImages: List<Image>? = null

    suspend fun writeImageToBase(image: Image): List<Image> {
        withContext(dispatcher) {
            imagesDao.insert(image.toImageEntity())
            insertInMemory(image)
        }

        return addedImages ?: emptyList()
    }

    private fun insertInMemory(image: Image) {
        addedImages = addedImages?.toMutableList()?.let { photos ->
            photos.add(image)
            photos
        } ?: listOf(image)
    }

    suspend fun getAll(): List<Image> {
        if (addedImages == null) {
            addedImages = withContext(dispatcher) {
                imagesDao.getAll().map { it.toImage() }
            }
        }

        return addedImages ?: emptyList()
    }
}