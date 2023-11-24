package com.example.albumapp.ui.theme.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumapp.domain.model.album.AlbumResponse
import com.example.albumapp.domain.model.photos.PhotosResponse
import com.example.albumapp.domain.model.user.UsersResponse
import com.example.albumapp.domain.useCase.albumsUseCase.AlbumsUseCase
import com.example.albumapp.domain.useCase.usersUseCase.UsersUseCase
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val albumsUseCase: AlbumsUseCase
) : ViewModel() {
    private val _users: MutableStateFlow<DataState<UsersResponse>?> = MutableStateFlow(null)
    var users: StateFlow<DataState<UsersResponse>?> = _users
    private val _albums: MutableStateFlow<DataState<AlbumResponse>?> = MutableStateFlow(null)
    var albums: StateFlow<DataState<AlbumResponse>?> = _albums
    private val _photos: MutableStateFlow<DataState<PhotosResponse>?> = MutableStateFlow(null)
    var photos: StateFlow<DataState<PhotosResponse>?> = _photos

    fun getUsers() {
        viewModelScope.launch {
            usersUseCase.getUsers().onStart {
                _users.emit(DataState.Loading(true))
            }.catch {
                _users.emit(DataState.Loading(false))
                _users.emit(DataState.Failure(it))
            }.collect {
                _users.emit(DataState.Loading(false))
                _users.emit(DataState.Success(it))
                getAlbums(it.random().id!!)
            }
        }
    }

    fun getAlbums(id: Int) {

        viewModelScope.launch {
            albumsUseCase.getAlbums(id).onStart {
                _albums.emit(DataState.Loading(true))
            }.catch {
                _albums.emit(DataState.Loading(false))
                _albums.emit(DataState.Failure(it))
            }.collect {
                _albums.emit(DataState.Success(it))
            }
        }
    }
}