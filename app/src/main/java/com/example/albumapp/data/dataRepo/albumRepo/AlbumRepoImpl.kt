package com.example.albumapp.data.dataRepo.albumRepo

import com.example.albumapp.data.dataSource.remote.ApiService
import com.example.albumapp.domain.domainRepo.albumRepo.AlbumRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AlbumRepoImpl(private val apiService: ApiService) : AlbumRepo {
    override suspend fun getAlbumFromRemote(id: Int) = flow {
        emit(apiService.getAlbums(id))
    }.flowOn(Dispatchers.IO)
}
