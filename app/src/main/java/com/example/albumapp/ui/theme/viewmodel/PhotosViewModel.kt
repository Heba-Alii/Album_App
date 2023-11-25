package com.example.albumapp.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumapp.domain.model.photos.PhotosResponse
import com.example.albumapp.domain.useCase.photosUseCase.PhotosUseCase
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosUseCase: PhotosUseCase) : ViewModel() {
    private val _photos: MutableStateFlow<DataState<PhotosResponse>?> = MutableStateFlow(null)
    var photos: StateFlow<DataState<PhotosResponse>?> = _photos
    fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            photosUseCase.getPhotos(albumId).onStart {
                _photos.emit(DataState.Loading(true))
            }.catch {
                _photos.emit(DataState.Loading(false))
                _photos.emit(DataState.Failure(it))
            }.collect {
                _photos.emit(DataState.Loading(false))
                _photos.emit(DataState.Success(it))
            }
        }
    }
}