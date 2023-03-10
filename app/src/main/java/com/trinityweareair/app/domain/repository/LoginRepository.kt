package com.trinityweareair.app.domain.repository

import com.trinityweareair.app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginAccount(strEmail : String , strPassword : String) : Flow<Resource<Boolean>>
}