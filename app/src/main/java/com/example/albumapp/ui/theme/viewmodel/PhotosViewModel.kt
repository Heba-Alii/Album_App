package com.example.albumapp.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumapp.domain.model.photos.PhotosResponse
import com.example.albumapp.domain.model.photos.PhotosResponseItem
import com.example.albumapp.domain.useCase.photosUseCase.PhotosUseCase
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosUseCase: PhotosUseCase) : ViewModel() {
    private val _photos: MutableStateFlow<DataState<PhotosResponse>?> = MutableStateFlow(null)
    var photos: StateFlow<DataState<PhotosResponse>?> = _photos
    private val _filteredPhotos: MutableLiveData<List<PhotosResponseItem>> = MutableLiveData()
    var filteredPhotos: LiveData<List<PhotosResponseItem>> = _filteredPhotos
    private val _allPhotos = MutableLiveData<List<PhotosResponseItem>>()

    //    private val _search: MutableLiveData<String> = MutableLiveData()
//    var search: MutableLiveData<String> = _search
//    private var allPhotoBeforeFiltered: PhotosResponse? = null
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
                // allPhotoBeforeFiltered = it
                _filteredPhotos.value = it
                _allPhotos.value = it
            }

        }
    }


    //    fun searchPhotos(query: String) {
//        val filteredList = allPhotoBeforeFiltered?.filter { image ->
//            image!!.title!!.contains(query, ignoreCase = true)
//        }
//        _filteredPhotos.value = filteredList!!
//    }
    fun searchPhotos(query: String) {
        val allPhotos = _allPhotos.value ?: emptyList()
        val filteredList =
            allPhotos.filter { photo -> photo.title!!.contains(query, ignoreCase = true) }
        _filteredPhotos.value = filteredList

    }

}