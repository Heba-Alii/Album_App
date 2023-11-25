package com.example.albumapp.domain.domainRepo.photosRepo

import com.example.albumapp.domain.model.photos.PhotosResponse
import kotlinx.coroutines.flow.Flow

interface PhotosRepo {
    suspend fun getPhotosFromRemote(albumId: Int): Flow<PhotosResponse>
}