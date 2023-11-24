package com.example.albumapp.domain.useCase.usersUseCase

import com.example.albumapp.domain.domainRepo.userRepo.UserRepo

class UsersUseCase(private val userRepo: UserRepo) {
    suspend fun getUsers() = userRepo.getUserFromRemote()
}