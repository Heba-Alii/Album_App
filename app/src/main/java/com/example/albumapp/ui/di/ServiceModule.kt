package com.example.albumapp.ui.di

import com.example.albumapp.data.dataRepo.albumRepo.AlbumRepoImpl
import com.example.albumapp.data.dataRepo.userRepo.UserRepoImpl
import com.example.albumapp.data.dataSource.remote.ApiService
import com.example.albumapp.domain.domainRepo.albumRepo.AlbumRepo
import com.example.albumapp.domain.domainRepo.userRepo.UserRepo
import com.example.albumapp.domain.useCase.albumsUseCase.AlbumsUseCase
import com.example.albumapp.domain.useCase.usersUseCase.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRepo(apiService: ApiService): UserRepo {
        return UserRepoImpl(apiService)
    }

    @Provides
    fun provideUseCase(userRepo: UserRepo): UsersUseCase {
        return UsersUseCase(userRepo)
    }

    @Provides
    fun provideAlbumRepo(apiService: ApiService): AlbumRepo {
        return AlbumRepoImpl(apiService)
    }

    @Provides
    fun provideAlbumUseCase(albumRepo: AlbumRepo): AlbumsUseCase {
        return AlbumsUseCase(albumRepo)
    }
}