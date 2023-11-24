package com.example.albumapp.data.dataRepo.userRepo

import com.example.albumapp.data.dataSource.remote.ApiService
import com.example.albumapp.domain.domainRepo.userRepo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepoImpl(private val apiService: ApiService) : UserRepo {
    override suspend fun getUserFromRemote() = flow {
        emit(apiService.getUser())
    }.flowOn(Dispatchers.IO)

}