package com.example.albumapp.data.dataSource.remote

import com.example.albumapp.domain.model.AlbumResponse
import com.example.albumapp.domain.model.PhotosResponse
import com.example.albumapp.domain.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val users = "users"
        const val albums = "albums"
        const val photos = "photos?albumId"
    }

    @GET(users)
    suspend fun getUser(): Response<UsersResponse>

    @GET(albums)
    suspend fun getAlbums(@Query("userId") userId: Int): Response<AlbumResponse>

    @GET(photos)
    suspend fun getPhotos(@Query("albumId") albumId: Int): Response<PhotosResponse>
}