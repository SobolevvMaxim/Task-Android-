package com.example.taskandroid

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.*

class ImagesRepository(
    private val imagesDao: ImagesDao,
    private val dispatcher: CoroutineDispatcher
) {
    private var addedImages: List<ImageEntity>? = null

    suspend fun writeImageToBase(image: Image): List<Image> {
        withContext(dispatcher) {
            val imageEntity = image.toImageEntity(UUID.randomUUID().toString())
            imagesDao.insert(imageEntity)
            insertInMemory(imageEntity)
        }

        return addedImages?.map { it.toImage() } ?: emptyList()
    }

    private fun insertInMemory(image: ImageEntity) {
        addedImages = addedImages?.toMutableList()?.let { photos ->
            photos.add(image)
            photos
        } ?: listOf(image)
    }
}