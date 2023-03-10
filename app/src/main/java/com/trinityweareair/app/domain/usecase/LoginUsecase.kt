package com.trinityweareair.app.domain.usecase

import com.trinityweareair.app.domain.repository.LoginRepository
import com.trinityweareair.app.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUsecase @Inject constructor(val loginRepository: LoginRepository) {

    suspend operator fun invoke(strEmail: String, strPassword: String): Flow<Resource<Boolean>> {
        return loginRepository.loginAccount(strEmail, strPassword)
    }
}