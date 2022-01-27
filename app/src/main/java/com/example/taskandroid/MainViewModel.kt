package com.example.taskandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ImagesRepository = ImagesRepository(App.getImagesDao(), Dispatchers.IO)
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        _errorLiveData.postValue(t.toString())
    }

    private val _photosLiveData = MutableLiveData<List<Image>>()
    val photosLiveData: LiveData<List<Image>> get() = _photosLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun loadImageToBase(image: Image) {
        viewModelScope.launch(exceptionHandler) {
            _photosLiveData.postValue(repository.writeImageToBase(image))
        }
    }
}