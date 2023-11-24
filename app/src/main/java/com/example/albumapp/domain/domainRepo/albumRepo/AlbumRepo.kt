package com.example.albumapp.domain.domainRepo.albumRepo

import com.example.albumapp.domain.model.album.AlbumResponse
import java.util.concurrent.Flow

interface AlbumRepo {
    suspend fun getAlbumFromRemote(id: Int): kotlinx.coroutines.flow.Flow<AlbumResponse>
}