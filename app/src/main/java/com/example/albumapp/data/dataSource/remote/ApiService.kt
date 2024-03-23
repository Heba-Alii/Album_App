package com.example.albumapp.data.dataSource.remote

import com.example.albumapp.domain.model.album.AlbumResponse
import com.example.albumapp.domain.model.photos.PhotosResponse
import com.example.albumapp.domain.model.user.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val users = "users"
        const val albums = "albums"
        const val photos = "photos"
    }

    @GET(users)
    suspend fun getUser(): UsersResponse

    @GET(albums)
    suspend fun getAlbums(@Query("userId") userId: Int): AlbumResponse

    @GET(photos)
    suspend fun getPhotos(@Query("albumId") albumId: Int): PhotosResponse
}