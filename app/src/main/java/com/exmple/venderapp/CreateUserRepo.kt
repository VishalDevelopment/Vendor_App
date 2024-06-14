package com.exmple.venderapp

import com.exmple.venderapp.Model.CreateUserApi
import com.exmple.venderapp.Response
import kotlinx.coroutines.flow.Flow


interface CreateUserRepo {
    suspend fun  createUser(): Flow<Response<CreateUserApi>>

}