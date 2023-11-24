package com.example.albumapp.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumapp.domain.model.album.AlbumResponse
import com.example.albumapp.domain.model.photos.PhotosResponse
import com.example.albumapp.domain.model.user.UsersResponse
import com.example.albumapp.domain.useCase.usersUseCase.UsersUseCase
import com.example.albumapp.ui.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usersUseCase: UsersUseCase) : ViewModel() {
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
            }
        }
    }
}