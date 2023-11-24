package com.example.albumapp.domain.useCase.albumsUseCase

import com.example.albumapp.domain.domainRepo.albumRepo.AlbumRepo

class AlbumsUseCase(private val albumRepo: AlbumRepo) {
    suspend fun getAlbums(id: Int) = albumRepo.getAlbumFromRemote(id)
}