package com.example.albumapp.domain.domainRepo.userRepo

import com.example.albumapp.domain.model.user.UsersResponse
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun getUserFromRemote(): Flow<UsersResponse>
}