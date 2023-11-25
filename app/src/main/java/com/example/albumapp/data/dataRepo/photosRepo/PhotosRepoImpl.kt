package com.example.albumapp.data.dataRepo.photosRepo

import com.example.albumapp.data.dataSource.remote.ApiService
import com.example.albumapp.domain.domainRepo.photosRepo.PhotosRepo
import com.example.albumapp.domain.model.photos.PhotosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PhotosRepoImpl(private val apiService: ApiService) : PhotosRepo {
    override suspend fun getPhotosFromRemote(albumId:Int): Flow<PhotosResponse> {
        return flow {
            emit(apiService.getPhotos(albumId))
        }.flowOn(Dispatchers.IO)
    }

}