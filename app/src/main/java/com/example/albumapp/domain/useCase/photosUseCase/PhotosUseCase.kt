package com.example.albumapp.domain.useCase.photosUseCase

import com.example.albumapp.domain.domainRepo.photosRepo.PhotosRepo

class PhotosUseCase(private val photosRepo: PhotosRepo) {
    suspend fun getPhotos(albumId: Int) = photosRepo.getPhotosFromRemote(albumId)

}