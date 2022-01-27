package com.example.taskandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PhotosRepository = PhotosRepository(Dispatchers.IO)
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        _errorLiveData.postValue(t.toString())
    }

    private val _photosLiveData = MutableLiveData<List<ImageItem>>()
    val photosLiveData: LiveData<List<ImageItem>> get() = _photosLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun loadPhotoToBase(imageItem: ImageItem) {
        viewModelScope.launch(exceptionHandler) {
            _photosLiveData.postValue(repository.writeImageToBase(imageItem))
        }
    }
}